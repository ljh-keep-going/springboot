package com.baidu.supermarket.controller;

import com.baidu.supermarket.pojo.Product;
import com.baidu.supermarket.service.ProductService;
import com.baidu.supermarket.util.AjaxResult;
import com.baidu.supermarket.util.PageResult;
import com.baidu.supermarket.util.TransFerPlace;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/prod")
public class ProductController {

    @Autowired
    private ProductService service;


    //跳转到首页
    @RequestMapping("/index.html")
    public String index(HttpServletRequest request,
                        @RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage){
        System.out.println("进入index首页");
        System.out.println("currentPage:"+currentPage);

        //设置当前页，发送到页面
        request.setAttribute("pages",currentPage);
        request.setAttribute("totalCount",service.count());

        //查询所有商品信息
        List<Product> list=service.getAllProduct(currentPage,6);
        request.setAttribute("list",list);

        return "index";

    }

    //根据id查询指定的商品信息
    @RequestMapping("/getProdById/{id}")
    public String getProdById(@PathVariable Integer id,HttpServletRequest request){
        System.out.println("根据id查询指定的商品信息："+id);

        //根据id查询指定的商品信息
        Product product=service.findById(id);

        request.setAttribute("product",product);
        return "prod/info";
    }

    @RequestMapping("/toList")
    public String toList() {
        System.out.println("prod.toList");
        return "prod/list";
    }

//    分页查询
    @PostMapping("/getPage")
    @ResponseBody
    public PageResult<Product> queryByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "limit", defaultValue = "5") Integer size,
                                           @RequestParam(name="pname", defaultValue = "") String prodName) {
//      查询满足查询条件的，页号指定的数据
        return service.queryByPage(page, size, prodName);
    }
//
//    跳转到新增窗口
    @RequestMapping("/toAdd")
    public String toAdd() {
        System.out.println("进入添加商品页面");
        return "prod/add";
    }

    //图片上传
    @RequestMapping("/upload")
    @ResponseBody
    public Map<String,Object> upload(MultipartFile file,HttpServletRequest request){
        System.out.println("执行文件上传：");
        System.out.println("原始文件名称："+file.getOriginalFilename());


        //获取项目根路径
        String path=request.getServletContext().getRealPath("/img/");
        System.out.println("path:"+path);
        File pathFile=new File(path);
        System.out.println("查看目录是否存在:"+pathFile.exists());

        if(!pathFile.exists()){ ////若不存在该目录，则创建目录
            pathFile.mkdir();
        }

        //生成真实文件名,解决文件重名问题
        String realName= UUID.randomUUID()+file.getOriginalFilename();

        //执行文件上传,将存储路径与真实文件名组装成一个File对象
        //存储到服务器的临时路径，存储到该路径时，当重启服务器则会又重新创建一个零时路径，所以如果重启则不能使用上传的图片
        File realFile=new File(pathFile,realName);

        try{
            //文件上传到服务器
            file.transferTo(realFile);

            //存储到项目的resources/static/img中,如果存储到该位置，重启服务器以后图片不会丢失，
            // 只有重启服务器以后才能使用，因为刚上传的图片没有发布到服务器
            //将上传的文件拷贝到target/classes/static/img目录中
            TransFerPlace.transfer(realFile);
        }catch (Exception e){
            e.printStackTrace();
        }



        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","文件上传成功");
        map.put("data","/img/"+realName);

        return map;
    }

    //执行添加操作
    @RequestMapping("/doAdd")
    @ResponseBody
    public AjaxResult doAdd(Product prod) {
        System.out.println("执行添加商品操作");
//        System.out.println("doAdd for prod={}", prod);


        int i = service.addOne(prod);
        if(i == 1) {
            return AjaxResult.right();
        }
        return AjaxResult.error();
    }


    //    跳转到订单月度分析
    @RequestMapping("/toView1")
    public String toView1(Model model) {
        model.addAttribute("echartTitle", "2022年度销售分析");
//        生成x轴坐标
        StringBuffer xaxis = new StringBuffer();
        for(int i=1; i<=12; i++) {
            xaxis.append(i+"月,");
        }
        String x = xaxis.toString();
        if(x.endsWith(",")) x = x.substring(0, x.length()-1);
        model.addAttribute("echartXAxis", x);

        StringBuffer xdata = new StringBuffer();
        List<Map<String, Object>> maps = service.queryMonthSal(2022);
        for(int i=1; i<=12; i++) {
            boolean setFlag = true;
            for(Map<String, Object> m : maps) {
                Object month = m.get("month");
//                log.debug("i={}, month={}", i, month);
                int monthNum = Integer.parseInt(month.toString());
                if(i == monthNum) {
                    xdata.append(m.get("sal") + ",");
                    setFlag = false;
                    break;
                }
            }
            if(setFlag) xdata.append("0,");
        }
        String y = xdata.toString();
        if(y.endsWith(",")) y = y.substring(0, y.length()-1);

        model.addAttribute("echartSal", y);
        return "/prod/view1";
    }

    //    跳转到销售占比分析
    @RequestMapping("/toView2")
    public String toView2(Model model) {
//        在这里查询，准备号数据，放入model
        List<Map<String, Object>> maps = service.queryTotalRatio();
        model.addAttribute("total", maps);
        return "/prod/view2";
    }
//


    @PostMapping("/batchDel")
    @ResponseBody
    public AjaxResult doDel(@RequestParam("prodIds[]") Integer[] prodIds) {
        int i = service.batchDel(prodIds);
        if(i == prodIds.length) {
            return AjaxResult.right();
        }
        return AjaxResult.error();
    }

    @PostMapping("/doDel/{prodId}")
    @ResponseBody
    public AjaxResult doDel(@PathVariable Integer prodId) {
//        调用service的删除功能
        int i = service.delProduct(prodId);
        if(i == 1) {
            return AjaxResult.right();
        }
        return AjaxResult.error();
    }
//
//    跳转到修改窗口
    @GetMapping("/toEdit/{prodId}")
    public String toEdit(@PathVariable Integer prodId, Model model) {
//        根据prodId 查询出商品对象，放入到model
        model.addAttribute("editProd", service.findById(prodId));
        return "prod/edit";
    }

    @PostMapping("/doEdit")
    @ResponseBody
    public AjaxResult doEdit(Product prod) {
        System.out.println("执行修改操作："+prod);
//        调用service的删除功能
        int i = service.editById(prod);
        if(i == 1) {
            return AjaxResult.right();
        }
        return AjaxResult.error();
    }
}
