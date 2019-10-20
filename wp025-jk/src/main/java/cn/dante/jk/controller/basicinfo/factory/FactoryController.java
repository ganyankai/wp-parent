package cn.dante.jk.controller.basicinfo.factory;

import cn.dante.jk.controller.BaseController;
import cn.dante.jk.domain.Factory;
import cn.dante.jk.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class FactoryController extends BaseController {
    @Autowired
    private FactoryService factoryService;

    //列表
    @RequestMapping("/basicinfo/factory/list.action")
    public String list(Model model){
        List<Factory> dataList = factoryService.find(null);
        model.addAttribute("dataList", dataList);			//传递结果集到页面

        return "/basicinfo/factory/jFactoryList.jsp";			//逻辑名
    }

    //转向新增页面
    @RequestMapping("/basicinfo/factory/tocreate.action")
    public String tocreate(){
        return "/basicinfo/factory/jFactoryCreate.jsp";
    }

    //新增保存
    @RequestMapping("/basicinfo/factory/insert.action")
    public String insert(Factory factory){
        factoryService.insert(factory);
        return "redirect:/basicinfo/factory/list.action";
    }

    //转向修改页面
    @RequestMapping("/basicinfo/factory/toupdate.action")
    public String toupdate(String id,Model model){
        //Serializable可以通用整形和字符串
        Factory obj = factoryService.get(id);
        model.addAttribute(obj);
        return "/basicinfo/factory/jFactoryUpdate.jsp";
    }

    //修改保存
    @RequestMapping("/basicinfo/factory/update.action")
    public String update(Factory factory){
        factoryService.update(factory);
        return "redirect:/basicinfo/factory/list.action";
    }

    //删除一个
    @RequestMapping("/basicinfo/factory/deleteById.action")
    public String deleteById(String id){
        factoryService.deleteById(id);
        return "redirect:/basicinfo/factory/list.action";
    }

    //删除多条
    @RequestMapping("/basicinfo/factory/delete.action")
    public String delete(@RequestParam("id") String[] ids){
        factoryService.delete(ids);
        return "redirect:/basicinfo/factory/list.action";
    }
}
