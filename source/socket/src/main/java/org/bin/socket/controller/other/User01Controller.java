package org.bin.socket.controller.other;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bin.socket.constant.Constant;
import org.bin.socket.entity.Users;
import org.bin.socket.service.LoginLogService;
import org.bin.socket.service.UsersService;
import org.bin.socket.util.UploadUtil;
import org.bin.socket.vo.User01VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * NAME   :  WebChat/com.amayadream.webchat.controller
 * Author :  Amayadream
 * Date   :  2016.01.09 17:56
 * TODO   :  用户控制器
 */
@Controller
@SessionAttributes(Constant.LOGIN_SESSION)
public class User01Controller {
	@Autowired
	private LoginLogService loginLogService;
	@Autowired
	private UsersService usersService;

    /**
     * 聊天主页
     */
    @RequestMapping(value = "chat")
    public ModelAndView getIndex(){
        ModelAndView view = new ModelAndView("index");
        return view;
    }

    /**
     * 显示个人信息页面
     */
    @RequestMapping(value = "{userid}", method = RequestMethod.GET)
    public ModelAndView selectUserByUserid(@PathVariable("userid") String userid, @ModelAttribute("userid") String sessionid){
        ModelAndView view = new ModelAndView("information");
        Users users = usersService.queryUsersByAccount(userid);
        User01VO vo = new User01VO();
        vo.convertPOToVO(users);
        view.addObject("user", vo);
        return view;
    }

    /**
     * 显示个人信息编辑页面
     * @param userid
     * @param sessionid
     * @return
     */
    @RequestMapping(value = "{userid}/config")
    public ModelAndView setting(@PathVariable("userid") String userid, @ModelAttribute("userid") String sessionid){
        ModelAndView view = new ModelAndView("info-setting");
        Users users = usersService.queryUsersByAccount(userid);
        User01VO vo = new User01VO();
        vo.convertPOToVO(users);
        view.addObject("user", vo);
        return view;
    }

    /**
     * 更新用户信息
     * @param userid
     * @param sessionid
     * @param user
     * @return
     */
    @RequestMapping(value = "{userid}/update", method = RequestMethod.POST)
    public String  update(@PathVariable("userid") String userid, @ModelAttribute("userid") String sessionid, Users user, RedirectAttributes attributes){
    	  usersService.updateUser(user);
//        if(flag){
//            logService.insert(logUtil.setLog(userid, date.getTime24(), defined.LOG_TYPE_UPDATE, defined.LOG_DETAIL_UPDATE_PROFILE, netUtil.getIpAddress(request)));
//            attributes.addFlashAttribute("message", "["+userid+"]资料更新成功!");
//        }else{
//            attributes.addFlashAttribute("error", "["+userid+"]资料更新失败!");
//        }
        return "redirect:/{userid}/config";
    }

    /**
     * 修改密码
     * @param userid
     * @param oldpass
     * @param newpass
     * @return
     */
    @RequestMapping(value = "{userid}/pass", method = RequestMethod.POST)
    public String changePassword(@PathVariable("userid") String userid, String oldpass, String newpass, RedirectAttributes attributes, HttpServletRequest request){
    	 Users users = usersService.queryUsersByAccount(userid);
        if(oldpass.equals(users.getPassword())){
        	users.setPassword(newpass);
            usersService.updateUser(users);
//            if(flag){
//            	loginLogService.addLoginLogLocal(loginLog)
////                logService.insert(logUtil.setLog(userid, date.getTime24(), defined.LOG_TYPE_UPDATE, defined.LOG_DETAIL_UPDATE_PASSWORD, netUtil.getIpAddress(request)));
//                attributes.addFlashAttribute("message", "["+userid+"]密码更新成功!");
//            }else{
//                attributes.addFlashAttribute("error", "["+userid+"]密码更新失败!");
//            }
        }else{
            attributes.addFlashAttribute("error", "密码错误!");
        }
        return "redirect:/{userid}/config";
    }

    /**
     * 头像上传
     * @param userid
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "{userid}/upload")
    public String upload(@PathVariable("userid") String userid, MultipartFile file, HttpServletRequest request, RedirectAttributes attributes){
        try{
            String fileurl = UploadUtil.upload(request, "upload", userid);
            Users users = usersService.queryUsersByAccount(userid);
            users.setAvatarPic(fileurl);
            usersService.updateUser(users);
//            if(flag){
//                logService.insert(logUtil.setLog(userid, date.getTime24(), defined.LOG_TYPE_UPDATE, defined.LOG_DETAIL_UPDATE_PROFILEHEAD, netUtil.getIpAddress(request)));
//                attributes.addFlashAttribute("message", "["+userid+"]头像更新成功!");
//            }else{
//                attributes.addFlashAttribute("error", "["+userid+"]头像更新失败!");
//            }
        } catch (Exception e){
            attributes.addFlashAttribute("error", "["+userid+"]头像更新失败!");
        }
        return "redirect:/{userid}/config";
    }

    /**
     * 获取用户头像
     * @param userid
     */
    @RequestMapping(value = "{userid}/head")
    public void head(@PathVariable("userid") String userid, HttpServletRequest request, HttpServletResponse response){
        try {
        	Users users = usersService.queryUsersByAccount(userid);
            String path = users.getAvatarPic();
            String rootPath = request.getSession().getServletContext().getRealPath("/");
            String picturePath = rootPath + path;
            response.setContentType("image/jpeg; charset=UTF-8");
            ServletOutputStream outputStream = response.getOutputStream();
            FileInputStream inputStream = new FileInputStream(picturePath);
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, i);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            outputStream = null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
