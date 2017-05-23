package cn.spiderpig.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.spiderpig.demo.domain.User;

@RepositoryDefinition(domainClass = User.class, idClass = Integer.class)
public interface UserRepository { //extends Repository<User,Integer>{

    public User findByName(String name);

    // where name like ?% and age <?
    public List<User> findByNameStartingWithAndAgeLessThan(String name, Integer age);

    /*  // where name like %? and age <?
    public List<User> findByNameEndingWithAndAgeLessThan(String name, Integer age);

    // where name in (?,?....) or age <?
    public List<User> findByNameInOrAgeLessThan(List<String> names, Integer age);

    // where name in (?,?....) and age <?
    public List<User> findByNameInAndAgeLessThan(List<String> names, Integer age);

    //Query里面的User是实体类
    @Query("select u from User u where id=(select max(id) from User u1)")
    public User getEmployeeByMaxId();

    @Query("select u from User u where u.name=?1 and u.age=?2")
    public List<User> queryParams1(String name, Integer age);

    @Query("select u from User u where u.name=:name and u.age=:age")
    public List<User> queryParams2(@Param("name") String name, @Param("age") Integer age);

    @Query("select u from User u where u.name like %?1%")
    public List<User> queryLike1(String name);

    @Query("select u from User u where u.name like %:name%")
    public List<User> queryLike2(@Param("name") String name);

    //可以用原生的sql语句
    @Query(nativeQuery = true, value = "select count(1) from user")
    public long getCount();*/

    //更新操作是要结合@Modifying注解使用
    @Modifying
    @Transactional
    @Query("update User u set u.age = :age where u.id = :id")
    public void update(@Param("id") Integer id, @Param("age") Integer age);

}
