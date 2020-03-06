package test;

import com.wws.dao.UserInfoDao;
import com.wws.model.UserInfo;
import com.wws.service.impl.UserInfoServiceImpl;
import com.wws.vo.PageVO;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestUser {
    @Test
    public void testUser(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserInfoDao userInfoDao = ctx.getBean("userInfoDao", UserInfoDao.class);
        /*List<UserInfo> list = userInfoDao.findAll(0,10);
        for(UserInfo user:list){
            System.out.println(user.getUserName());
        }*/
        UserInfoServiceImpl impl = ctx.getBean("userInfoServiceImpl",UserInfoServiceImpl.class);
        //PageVO vo = impl.findAll(1,10);
        //System.out.println(vo);
        //System.out.println(vo.getCount());
    }
}
