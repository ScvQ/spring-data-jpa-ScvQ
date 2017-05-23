package cn.spiderpig.demo;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import cn.spiderpig.demo.domain.User;
import cn.spiderpig.demo.repository.UserJpaRepository;
import cn.spiderpig.demo.repository.UserRepository;
import cn.spiderpig.demo.service.UserService;

public class MessTest {
    
    private UserRepository userRepository;
    private UserJpaRepository userJpaRepository;
    private ApplicationContext ctx;
    
    private UserService userService;
    
    @Before
    public void before(){
        ctx = new ClassPathXmlApplicationContext("beans.xml");
        userRepository = ctx.getBean(UserRepository.class);
        userJpaRepository = ctx.getBean(UserJpaRepository.class);
        userService = ctx.getBean(UserService.class);
    }
    
    @Test
    public void findByName(){
        
        User user = userRepository.findByName("aaa");
        System.out.println(user.getName());
        
    }
    
    @Test
    public void messTest(){
        /*List<User> list = userRepository.findByNameStartingWithAndAgeLessThan("a", 12);
        for(User user:list){
            System.err.println(user.getName());
        }*/
        
        //测试这个的时候遇到了问题，解决了很久，hibernate的事务管理不同的版本不一样，我开始用的5.2.10.Final，这个方法一直测试不成功
        //后来改为14.3.6.Final，测试成功
        //userService.update(1, 33);
        
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(0, 3, sort);
        
        Specification<User> specification = new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                
                Path path = root.get("age");
                return cb.gt(path, 50);
            }
        };
        
        Page<User> page = userJpaRepository.findAll(specification, pageable);
        //总页数
        System.out.println(page.getTotalPages());
        //第一页的内容
        System.out.println(page.getContent());
        //当前第几页
        System.out.println(page.getNumber()+1);
        
        
    }

}
