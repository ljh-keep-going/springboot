package com.baidu.springboot07.controller;


import com.baidu.springboot07.pojo.Product;
import com.baidu.springboot07.service.ProductService;
import com.baidu.springboot07.utils.AjaxResult;
import com.baidu.springboot07.utils.PageResult;
import com.baidu.springboot07.utils.TransFerPlace;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author szc
 * @since 2024-04-17
 */
@Controller
@RequestMapping("/prod")
public class ProductController {

    @Autowired
    private ProductService productService;

    //根据id查询指定的商品信息，并跳转到/prod/info.html页面
    @RequestMapping("/getProductById/{id}")
    public String getProductById(@PathVariable("id") Integer pid,HttpServletRequest request){
        System.out.println("----------getProductById----------");
        System.out.println("pid:"+pid);

        //根据id查询指定的商品信息
        Product product=productService.getById(pid);

        //将商品信息保存到request中
        request.setAttribute("product",product);

        return "prod/info";
    }

    //进入index.html首页
    @RequestMapping("/index.html")
    public String toIndex(@RequestParam(defaultValue = "1") Integer currentPage,HttpServletRequest request){
        System.out.println("------------toIndex------------");
        System.out.println("currentPage:"+currentPage);

        //分页查询商品信息
        List<Product> list=productService.getProductByPage(currentPage,6);

        //查询总记录数
        long count=productService.count();

        //保存当前页
        request.setAttribute("pages",currentPage);

        //保存总记录数
        request.setAttribute("totalCount",count);

        //保存分页查询的商品信息
        request.setAttribute("list",list);

        return "index";
    }

    //跳转到显示商品的页面
    @Operation(summary = "跳转到产品列表页面", description = "参数是当前页码"  )
    @RequestMapping("/toList")
    public String toList() {
        System.out.println("-----------toList------------");

        return "prod/list";
    }

    // 普通分页查询商品信息
    @Operation(summary = "分页查询商品信息", description = "第一个参数表示模糊查询的关键字，第二个参数表示当前页，第三个参数表示页面容量"  )
    @RequestMapping("/getPage")
    @ResponseBody
    public PageResult<Product> getByPage(Integer page, Integer limit, String pname) {
        System.out.println("--------------getPage------------");
        System.out.println("pname:"+pname);
        System.out.println("page:"+page);
        System.out.println("limit:"+limit);

        if ("".equals(pname)){
            pname=null;
        }

        //普通分页查询与模糊分页
        PageResult<Product> pageResult = productService.getPage(pname,page, limit);

        return pageResult;
    }

    //根据id批量删除
    @Operation(summary = "根据id批量删除",description = "参数是一个主键的数组")
    @PostMapping("/batchDel")
    @ResponseBody
    public AjaxResult batchDel(@RequestParam("prodIds[]") Integer prodIds[]){
        System.out.println("-----batchDel-----");

//        for (int id:prodIds){
//            System.out.println("id:"+id);
//        }
//
//        //将整型数组转换成List<integer>集合
//        List<Integer> list=new ArrayList<>();
//        for (int i=0;i<prodIds.length;i++){
//            list.add(prodIds[i]);
//        }

        //将整型数组转换成List<integer>集合
        List<Integer> list = Arrays.asList(prodIds);

        //执行批量删除
        boolean flag=productService.removeByIds(list);

        if (flag){
            return AjaxResult.right();
        }else {
            return AjaxResult.error();
        }
    }

    //根据主键删除商品信息
    @PostMapping("/doDel/{pid}")
    @ResponseBody
    public AjaxResult doDel(@PathVariable("pid")Integer pid){
        System.out.println("------------doDel-----------");
        System.out.println("pid:"+pid);

        //执行删除
        boolean flag=productService.removeById(pid);
        if(flag){ //flag==true 删除成功
            return AjaxResult.right();
        }else {
            return AjaxResult.error();
        }
    }

    //跳转到添加页面
    @RequestMapping("/toAdd")
    public String toAdd(){
        System.out.println("-------------toAdd-------------");
        return "prod/add";
    }

    //处理图片上传
    @RequestMapping("/upload")
    @ResponseBody
    public Map<String ,Object> upload(MultipartFile file, HttpSession session){
        System.out.println("--------------upload-------------");
        System.out.println("file:"+file);
        //获取文件名
        String name= file.getOriginalFilename();
        System.out.println("name:"+name);

        //获取项目中img目录的路径
        String path=session.getServletContext().getRealPath("/img");
        System.out.println("path:"+path);

        //根据path，创建一个File对象，表示保存图片的目录
        File file1=new File(path);
        if(!file1.exists()){
            file1.mkdir(); //创建目录
        }

        //处理文件同名问题
        UUID uuid=UUID.randomUUID();
        String realName=uuid+name;

        //创建一个File对象，表示需要上传的文件
        File file2=new File(file1,realName);

        try {
            //执行文件上传
            file.transferTo(file2);
        }catch (Exception e){
            e.printStackTrace();
        }

        //将文件复制一份到target/static/img
        TransFerPlace.transfer(file2);

        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","文件上传成功");
        map.put("data","/img/"+realName);

        return map;
    }

    //添加商品
    @PostMapping("/doAdd")
    @ResponseBody
    public AjaxResult doAdd(Product product){
        System.out.println("---------------doAdd---------------");
        System.out.println("product:"+product);

        //执行添加
        boolean flag=productService.save(product);
        if(flag){
            return AjaxResult.right();
        }else {
            return AjaxResult.error();
        }
    }

    //跳转到修改页面，并根据id查询指定的商品信息
    @RequestMapping("/toEdit/{id}")
    public String toEdit(@PathVariable("id") Integer pid , HttpServletRequest request) {
        System.out.println("------------toEdit------------");
        System.out.println("pid:"+pid);

        //根据id查询指定的商品信息
        Product product=productService.getById(pid);

        //将商品信息保存到request作用域中
        request.setAttribute("product",product);

        return "prod/edit";
    }

    //执行修改操作
    @PostMapping("/doEdit")
    @ResponseBody
    public AjaxResult doEdit(Product product){
        System.out.println("------------doEdit------------");
        System.out.println("product:"+product);

        //执行修改功能
        boolean flag=productService.updateById(product);

        if(flag){
            return AjaxResult.right();
        }else {
            return AjaxResult.error();
        }
    }
}
