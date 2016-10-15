/**
 * Created by LINSHIRUI447 on 2016-09-02.
 */
appModule.controller("appCtrl",["$scope",'$window',function($scope,$window){
    $scope.viewTabs=[
        {tabName:"home",tabUrl:"#userCenter"},
        {tabName:"messages",tabUrl:"#authorizationMgt"},
        {tabName:"userInfo",tabUrl:"#userCenter"},
        {tabName:"help",tabUrl:"#userCenter"}
    ];
    $scope.selectedTab=1;
    $scope.selectTab=function(index){
        $scope.selectedTab=index;
    };
    $scope.setActiveTab=function(index){
        $scope.tabUrl = $scope.viewTabs[index].tabUrl;
        $window.location.href=$scope.tabUrl;
    };
    $scope.deleteTab=function(index){
        $scope.viewTabs.splice(index,1);
        if($scope.viewTabs.length==1){//当仅剩下主页tab时
            $scope.selectTab(0);
            var url=$scope.viewTabs[0].tabUrl;
            $scope.tabUrl = $scope.viewTabs[0].tabUrl;
            $window.location.href= url;//跳转到主页
        }else{//当tabs中存在非主页的tab时
            if($scope.viewTabs.length>index+1){//删除不为最右边的tab
                $scope.selectTab(index);
                var url=$scope.viewTabs[index].tabUrl;
                $scope.tabUrl = $scope.viewTabs[index].tabUrl;
                $window.location.href= url+'';//跳转
            }else{//删除最右边的tab
                $scope.selectTab(index-1);
                var url=$scope.viewTabs[index-1].tabUrl;
                $scope.tabUrl = $scope.viewTabs[index-1].tabUrl;
                $window.location.href= url;//跳转
            }
        }
    }

    $scope.refreshTab=function(index){
    };

    $scope.logout = function(){
        layer.confirm('您确认要退出系统？', {
            btn: ['确定','取消'] //按钮
        },function(){
            $http({
                url: web3Url+'/portal/logout',
                method:'post'
            }).success(function(resultData){
                if (resultData.code == "000000") {
                    location.href = "index.html";
                } else {
                    layer.msg(resultData.msg);
                }
            }).error(function(data,header,config,status){
                layer.msg('系统异常');
            });
        });
    };

    $scope.showTab = function(menu){

        //如果当前导航栏项有子项，则不触发tab页面功能，默认为具有子项
        var hasChild = menu.hasChild==null?true:menu.hasChild;
        if(hasChild){
            return;
        }

        var vt=$scope.viewTabs;
        var vtLen = vt.length;
        var isTabExist = false;
        var tabIndex = -1;
        var tabUrl = menu.url;
        var i=0;
        while(i<vtLen && isTabExist==false){
            var tmpTabUrl = vt[i].tabUrl;
            if(tmpTabUrl === tabUrl){  //用截取后的值判断，url带参数与不带参数都在同一个tab页中打开
                isTabExist=true;
                tabIndex=i;
            }
            i++;
        }
        if(isTabExist==false){
            $scope.viewTabs.push({tabName:menu.name,tabUrl:menu.url});
            $scope.selectedTab = $scope.viewTabs.length - 1; //set the newly added tab active.
        }else{
            $scope.selectedTab = tabIndex; //set the newly added tab active.
        }

    }

    $scope.navItems = [{name:"用户中心",href:"#userCenter",icon:"icon-user",hasChild:false},
        {name:"用户管理",href:"#",icon:"icon-desktop",hasChild:true,
            children:[{name:"用户列表",icon:"icon-double-angle-right",href:"#authorizationMgt",hasChild:false},
                {name:"权限管理",icon:"icon-double-angle-right",href:"#authorizationMgt",hasChild:false}]},
        {name:"导航栏事例",href:"#",icon:"icon-search",hasChild:true,
            children:[{name:"第一级菜单",icon:"icon-double-angle-right",href:"#",hasChild:true,
                         children:[{name:"第二级菜单",icon:"icon-leaf",href:"#",hasChild:false},
                             {name:"第二级结点",icon:"icon-pencil",href:"#",hasChild:false}]
            }]
        }
    ];
}]);