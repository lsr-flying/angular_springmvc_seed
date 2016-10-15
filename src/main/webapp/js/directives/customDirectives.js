/**
 * Created by LINSHIRUI447 on 2016-09-12.
 */
appModule.directive("helloDirective",function(){
   return{
       restrict:"E",
       scope:{
           name:"@",
           salary:"="
       },
       template:
           "<div>" +
               "<b>{{name}}</b>:"+
               "<input ng-model='salary'/>"+
           "</div>",
       replace:true,
       link:function(scope,element,attrs,controller){}
   }
});

//导航栏树
appModule.directive("sideBar",function(){
    return{
        restrict:"E",
        templateUrl:"views/components/navBarMenu.html",
        scope:{
            navItems:'=',
            itemClick:'&'
        },
    }
});