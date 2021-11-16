变量的分类：
    按照类型：基本变量和引用变量
    按照位置：成员变量和局部变量
方法的调用：虚方法和非虚方法
    非虚方法：静态方法，私有方法，final方法，实例构造器，父类方法（super）。invokespecial,invokestatic调用的方法
    虚方法：其它方法都是`**~~__~~**`。invokevirtual（final修饰除外），invokeinterface调用的方法
栈：局部变量表，操作数栈，动态连接，返回地址，一些其它信息