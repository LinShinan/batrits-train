package com.batrits.controller;

import com.batrits.anno.OperateLog;
import com.batrits.pojo.Dept;
import com.batrits.pojo.Result;
import com.batrits.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

//    private static final Logger log= LoggerFactory.getLogger("DeptController.class");
    @Autowired
    private DeptService deptService;

//    @RequestMapping(value="/depts",method= RequestMethod.GET)
    @GetMapping
    public Result findAll(){
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

//    @DeleteMapping("/depts")
//    public Result deleteById(HttpServletRequest request){
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//        deptService.delete(id);
//        System.out.println("删除id为"+id+"的部门");
//        return Result.success();
//    }

//    @DeleteMapping("/depts")
//    public Result deleteById(@RequestParam(value="id",required=false) Integer deptId){
//        deptService.delete(deptId);
//        System.out.println("删除id为"+deptId+"的部门");
//        return Result.success();
//    }

    @OperateLog
    @DeleteMapping
    public Result deleteById(Integer id){
        deptService.deleteById(id);
        log.info("删除id为{}的部门",id);
        return Result.success();
    }

//    @PostMapping("/depts")
//    public Result add(HttpServletRequest request) throws IOException {
//        // 获取请求体内容
//        BufferedReader reader = request.getReader();
//        StringBuilder body = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            body.append(line);
//        }
//
//        // 手动解析JSON（需要引入Jackson等JSON库）
//        ObjectMapper objectMapper = new ObjectMapper();
//        Dept dept = objectMapper.readValue(body.toString(), Dept.class);
//
//        deptService.add(dept);
//        System.out.println("添加部门"+dept.getName());
//        return Result.success();
//    }

    @OperateLog
    @PostMapping
    public Result add(@RequestBody Dept dept){
        deptService.add(dept);
        log.info("添加部门{},创建时间{}",dept.getName(),dept.getCreateTime());
        return Result.success();
    }

    /**
     * 根据id查询部门
     * @param deptId
     * @return Result
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Integer deptId){
        Dept dept=deptService.getById(deptId);
        return Result.success(dept);
    }

    @OperateLog
    @PutMapping
    public Result update(@RequestBody Dept dept){
        deptService.update(dept);
        log.info("修改部门"+dept);
        return Result.success(dept);
    }
}
