# Gatva-student-management-system-

## 概述  
本项目建立在一个已有的GUI图形界面上，利用Java实现数据增删改查。  
本项目的业务逻辑分别由以下六个包分别实现。

### controller包  
所有和业务处理、操作相关的代码，都应该写在这个包下。然后外界只要有业务操作需求，都需要依赖该包下的类，然后操作对象完成需求。
### dao包  
所有和程序数据直接交互的操作，即对数据进行直接增删改查等代码，都应该写在这个包下。然后每当控制层中的类需要进行数据操作时，都需要依赖该包下的类，然后操作对象完成需求。
### model包  
程序中数据的，数据类型定义，存储等代码都应该直接写在这个包下。说白了，这里面是放实体类的。在本程序中，实体层还负责模拟数据源。
### start包
启动包
### util包  
存放程序所需的工具类
### view包  
程序启动后，能够展示给用户的界面相关的代码，都放在这个包下。当GUI界面需要完成增删改查的业务操作时，就需要依赖控制层中的类，创建对象完成相关的功能。  

## 用户登录
在model包下的UserData类中，模拟存储能够用来登陆的用户名和密码  
view包下的类LoginFrame，要实现登陆功能，它需要依赖controller包下的UserController类  

## 展示学生数据  
我们增删改查的所有操作，都要基于StudentData类中的Studnet对象数组展开。  
现在这个"前端"要求你提供"接口"，获取：  
表头数据  
表格数据  
这两个需求对应两个方法，定义在StudentController中的getAllTableData()和getTableColums()。
>getAllTableData()方法的实现，主要就是封装Student对象数，组成一个String类型二维数组作为方法返回值。
getTableColumns方法是获取表头，表头的String数组已经直接在数据源StudentData中给出了。直接获取即可（但是需要在dao层获取，再返回给controller层，不能直接在controller层获取）  

## 插入学生数据  
###  数据校验  
这个校验逻辑的实现，依赖于util包下的工具类CheckAndHandleUtils。  
然后在dao中调用该工具类方法即可  
需要完成util包下工具类CheckAndHandleUtils中checkStuId、checkName、checkAge、checkCity、checkPhoneNum、checkEmail、checkGender这些方法的实现  
> 在代码中我们应该尽量避免魔法值的使用，使用我们用注解和配置文件代替魔法值  
### 新增一条学生信息  
点击主界面的新增信息，依次填入对应信息并保存  
这个校验逻辑的实现，需要依赖于util包下的工具类AddStudentFrame类。
而我们只需要在dao和controller中实现addStudent()方法即可  
> 整个插入的过程，仅需要将一个学生对象，插入数据源中就可以了。

## 将学生信息写入文件中  
为了读取方便，我们可以用对象流将每个Student对象写入到文件中（序列化过程）  
## 读入学生信息  
将保存在文件中的序列化信息以对象流的形式读入（反序列化过程）  
> 在init()函数中做相应的修改,需要创建输入流对象,进行反序列化操作。得到Student数组对象,进行遍历,直接放入STUDS数组中,就可以避免使用String的API解析。

## 删除一条学生信息  
在界面中选中一行待删除的数据，点击删除就可以将其删除  
点击是按钮后，"前端"就会将该条学生信息的学生学号（学号是唯一的）传给你，通过delStudent(String id)方法删除数据  

## 查询学生信息  
查询的功能严格来说，又分为两个部分。表格主页面的上方有一个选项框，可以选中查询的方式：  
按照学号查询  
按照姓名查询  

# 修改学生信息  


# 集合类的选取
## Map和Collection
HashMap存储键值对数据，按值查找的时间复杂度接近O(1)