package cn.gdrj.est.controller;

import cn.gdrj.est.common.util.ResultUtil;
import cn.gdrj.est.dto.Result;
import cn.gdrj.est.entity.Users;
import cn.gdrj.est.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("test")
public class TestController {

    // @RequestMapping(value="t1",method={RequestMethod.GET})
    @GetMapping("t1")
    public String test1(){
        return "test1 ok";
    }

    @Autowired
    private UsersService usersService;

    @GetMapping("/getuser")
    public Users getById(Long id){
        return usersService.findById(id);
    }

    @GetMapping("/getuser1")
    public Result<Users> getById1(Long id){
        Users users = usersService.findById(id);

//        Result<Users> result = new Result<>();
//        result.setCode("0");
//        result.setMsg("操作成功");
//        result.setData(users);
//        return result;

        return ResultUtil.success(users);
    }

}
