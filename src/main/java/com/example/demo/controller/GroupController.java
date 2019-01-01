package com.example.demo.controller;

import com.example.demo.dao.*;
import com.example.demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//@RestController
@Controller
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupingRepository groupingRepository;

    @Autowired
    private LogOrderRepository logOrderRepository;

    @Autowired
    private GroupInfoRepository groupInfoRepository;

    @Autowired
    private GroupOrderRepository groupOrderRepository;

    @Autowired
    private SystemUserRepository systemUserRepository;

    @RequestMapping(value = "/launch", method = RequestMethod.POST)
    public String launchGroup(HttpSession session,@RequestParam String theme, @RequestParam int numbers, @RequestParam int groupCount){

        ResponseFormate<GroupDetail> result = new ResponseFormate<GroupDetail>();

        Object obj = session.getAttribute("userDetail");
        SystemUser systemUser = (SystemUser) obj;
        SystemUser user = systemUserRepository.findByUsername(systemUser.getUsername());
        long userId = user.getId();

            // 记录请求的order
            LogOrder launchLogOrder = new LogOrder();
            launchLogOrder.genLogOrder("launchGroup",0,userId);

            System.out.println(launchLogOrder.toString()+"launchLogOrder");
            LogOrder saveLogOrder = logOrderRepository.save(launchLogOrder);
            System.out.println(saveLogOrder.toString()+"saveLogOrder");
            // 生成分组订单
            Grouping grouping = new Grouping();
            grouping.genGrouping(theme,groupCount,numbers,userId);
            Grouping saveGrouping = groupingRepository.save(grouping);
            //生成分组详情订单

        List<GroupInfo> groupInfos = new ArrayList<GroupInfo>(groupCount);

        for (int i = 0; i < groupCount; i++) {
            GroupInfo groupInfo = new GroupInfo();
            groupInfo.genGroupInfo(0,"A"+i,saveGrouping.getId());
                GroupInfo saveGroupInfo = groupInfoRepository.saveAndFlush(groupInfo);
                groupInfos.add(saveGroupInfo);
            }
            saveLogOrder.setStatus("success");
            saveLogOrder.setParentId(saveGrouping.getId());
            logOrderRepository.save(saveLogOrder);

        return "redirect:/group/"+saveGrouping.getId();
    }

    @RequestMapping(value = "/participate/{id}",method = RequestMethod.GET)
    public String participateGroup(@PathVariable Long id,HttpSession session, ModelMap map) {
        Object obj = session.getAttribute("userDetail");
        SystemUser systemUser = (SystemUser) obj;
        SystemUser user = systemUserRepository.findByUsername(systemUser.getUsername());
        long userId = user.getId();

        ResponseFormate<GroupDetail> result = new ResponseFormate<GroupDetail>();

        LogOrder participateOrder = new LogOrder();
        participateOrder.genLogOrder("participateGroup",id,userId);
        LogOrder saveOrder = logOrderRepository.saveAndFlush(participateOrder);

        Grouping grouping = groupingRepository.findByIdAndStatus(id,"valid");
        if(grouping ==null){
            saveOrder.setStatus("fail");
            logOrderRepository.save(saveOrder);
            result.setFailResponse("找不到该生效的分组订单");
            map.addAttribute("res",result);
            return "groupDetail";
        }


        int readyCount = 0;
        List<GroupInfo> infos = groupInfoRepository.findGroupInfosByThemeId(id);
        int length = infos.size();
        for(int i=0;i<length;i++){
            GroupInfo groupInfo = infos.get(i);
            readyCount+=groupInfo.getCount();
        }
        if(readyCount==grouping.getParticipatorCount()){
            saveOrder.setStatus("fail");
            logOrderRepository.save(saveOrder);
            result.setFailResponse("人数已满");
            map.addAttribute("res",result);
            return "groupDetail";
        }

        GroupOrder existsOrder = groupOrderRepository.findGroupOrderByUserIdAndStatusAndThemeId(userId,"valid",id);

        if(existsOrder!=null){
            result.setFailResponse("已经投过票");
            map.addAttribute("res",result);
            return "groupDetail";
        }

        int maxCount = Math.round(grouping.getParticipatorCount()/grouping.getGroupCount());
        GroupInfo groupInfo;
        List<GroupInfo> satisfiedInfos = groupInfoRepository.findGroupInfosByThemeIdAndCountBefore(id,maxCount);
        if(satisfiedInfos==null||satisfiedInfos.size()==0){
            int randomNum = (int)Math.random()*length;
            groupInfo = infos.get(randomNum);
        }else {
            int randomNum = (int)Math.random()*satisfiedInfos.size();
            groupInfo = satisfiedInfos.get(randomNum);
        }
        groupInfo.setCount(groupInfo.getCount()+1);
        groupInfoRepository.save(groupInfo);
        GroupOrder groupOrder = new GroupOrder();
        groupOrder.genGroupOrder(userId,groupInfo.getId(),id);
        groupOrderRepository.save(groupOrder);

        saveOrder.setStatus("success");
        logOrderRepository.save(saveOrder);
        return "redirect:/group/"+id;
    }

    @RequestMapping(value= "/delete/{id}",method = RequestMethod.GET)
    public String cancelGroup(@PathVariable Long id, HttpSession session, ModelMap map){

        Object obj = session.getAttribute("userDetail");
        SystemUser systemUser = (SystemUser) obj;
        SystemUser user = systemUserRepository.findByUsername(systemUser.getUsername());
        long userId = user.getId();

        ResponseFormate<Grouping> result = new ResponseFormate<Grouping>();

        // 生成取消订单
        LogOrder cancelOrder = new LogOrder();
        cancelOrder.genLogOrder("cancelGroup", id,userId);
        LogOrder saveOrder = logOrderRepository.save(cancelOrder);

        Grouping grouping = groupingRepository.findByIdAndStatus(id,"valid");
        if(grouping == null){
            saveOrder.setStatus("fail");
            logOrderRepository.save(saveOrder);
            result.setFailResponse("不存在该有效的分组订单");
            map.addAttribute("res", result);
            return "orders";
        }
        grouping.setStatus("invalid");
        groupingRepository.saveAndFlush(grouping);

        List<GroupOrder> groupOrders = groupOrderRepository.findGroupOrdersByThemeIdAndStatus(id,"valid");
        for(int i =0;i<groupOrders.size();i++){
            GroupOrder groupOrder = groupOrders.get(i);
            groupOrder.setStatus("invalid");
            groupOrderRepository.save(groupOrder);
        }
        saveOrder.setStatus("success");
        logOrderRepository.save(saveOrder);
        return "redirect:/group/orders";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String groupList(@PathVariable Long id,ModelMap map){

        ResponseFormate<GroupDetail> result = new ResponseFormate<GroupDetail>();
        Grouping grouping = groupingRepository.findByIdAndStatus(id,"valid");


        GroupDetail groupDetail = new GroupDetail();
        groupDetail.setThemeId(id);
        groupDetail.setThemeName(grouping.getTheme());
        List<GroupInfoDetail> list = new ArrayList<GroupInfoDetail>();
        List<GroupInfo> groupInfos = groupInfoRepository.findGroupInfosByThemeId(id);
        for(int i =0;i<groupInfos.size();i++){
            GroupInfoDetail groupInfoDetail = new GroupInfoDetail();
            groupInfoDetail.setGroupName(groupInfos.get(i).getGroupName());
            groupInfoDetail.setCount(groupInfos.get(i).getCount());
            List<GroupOrder> groupOrders = groupOrderRepository.findGroupOrdersByThemeIdAndStatusAndGroupId(id,"valid",groupInfos.get(i).getId());
            String [] members = new String[groupOrders.size()];
            System.out.println(i+"orders.lenght:"+groupOrders.size());
            for (int j = 0;j<groupOrders.size();j++){
                long eachUserId = groupOrders.get(j).getUserId();
                SystemUser user = systemUserRepository.findById(eachUserId);
                members[j] = user.getUsername();
            }
            groupInfoDetail.setGroupMember(members);
            list.add(groupInfoDetail);
        }
        groupDetail.setGroupInfoDetails(list);
        result.setSuccessResponse(groupDetail);
        System.out.println(result.getCode()+result.getMsg()+result.getData().getThemeName());
        map.addAttribute("res",result);
        return "groupDetail";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String groupOrdersList(HttpSession session,ModelMap map){
        Object obj = session.getAttribute("userDetail");
        SystemUser systemUser = (SystemUser) obj;
        SystemUser user = systemUserRepository.findByUsername(systemUser.getUsername());
        long userId = user.getId();
        ResponseFormate<List<UserGroupLogOrder>> result = new ResponseFormate<List<UserGroupLogOrder>>();

        List<String> typeList = new ArrayList<String>(Arrays.asList("launchGroup", "participateGroup"));
        List<LogOrder> logOrders = logOrderRepository.findLogOrdersByUserIdAndTypeInAndStatus(userId, typeList, "success");
        List<UserGroupLogOrder> userGroupLogOrders = new ArrayList<UserGroupLogOrder>(logOrders.size());
        for(int i=0;i<logOrders.size();i++){
            LogOrder logOrder = logOrders.get(i);
            Grouping grouping = groupingRepository.findByIdAndStatus(logOrder.getParentId(),"valid");
            if(grouping==null){
                continue;
            }
            if(grouping.getUserId()==logOrder.getUserId()&&logOrder.getType().equals("participateGroup")){
                continue;
            }
            UserGroupLogOrder userGroupLogOrder = new UserGroupLogOrder();
            userGroupLogOrder.setThemeId(grouping.getId());
            userGroupLogOrder.setThemeName(grouping.getTheme());
            userGroupLogOrder.setType(logOrder.getType());
            userGroupLogOrders.add(userGroupLogOrder);
        }

        result.setSuccessResponse(userGroupLogOrders);
        map.addAttribute("res",result);
        return "orders";

    }
}
