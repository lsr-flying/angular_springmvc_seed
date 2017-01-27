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
            itemClick:'&',
            curItemId:'='
        }
    }
});

//窗体tab控件
appModule.directive("viewTab",["$window",function($window){
    return{
        restrict:"E",
        templateUrl:"views/components/viewTab.html",
        scope:{
            tabItems:"=",
            selectedTab:"="
        },
        link:function($scope,ele,attrs){

            $scope.selectTab = function(index){
                $scope.selectedTab=index;
            };

            $scope.setActiveTab = function(index){
                $scope.tabUrl = $scope.tabItems[index].tabUrl;
                $window.location.href=$scope.tabUrl;
            };

            $scope.refreshTab = function () {

            };

            $scope.deleteTab = function(index){
                $scope.tabItems.splice(index,1);
                if($scope.tabItems.length==1){//当仅剩下主页tab时
                    $scope.selectTab(0);
                    var url=$scope.tabItems[0].tabUrl;
                    $scope.tabUrl = $scope.tabItems[0].tabUrl;
                    $window.location.href= url;//跳转到主页
                }else{//当tabs中存在非主页的tab时
                    if($scope.tabItems.length>index+1){//删除不为最右边的tab
                        $scope.selectTab(index);
                        var url=$scope.tabItems[index].tabUrl;
                        $scope.tabUrl = $scope.tabItems[index].tabUrl;
                        $window.location.href= url+'';//跳转
                    }else{//删除最右边的tab
                        $scope.selectTab(index-1);
                        var url=$scope.tabItems[index-1].tabUrl;
                        $scope.tabUrl = $scope.tabItems[index-1].tabUrl;
                        $window.location.href= url;//跳转
                    }
                }
            };
        }
    }
}]);

//消息通知栏
appModule.directive("notificationMenu",function(){
    return{
        restrict:"E",
        templateUrl:"views/components/notificationMenu.html",
        scope:{
            colorClass:'@',
            notNum:'@',
            notificationItems:'='
        },
        replace:true,
        link:function($scope,ele,attr){
            $scope.notificationItems = [{type:"中奖",num:88},
                {type:"吃饭",num:3}];
        }
    }
});

//用户配置菜单栏
appModule.directive("userSettingsMenu",function(){
    return{
        restrict:"E",
        templateUrl:"views/components/userSettingsMenu.html",
        replace:true
    }
});