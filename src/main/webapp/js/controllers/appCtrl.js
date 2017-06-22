/**
 * Created by LINSHIRUI447 on 2016-09-02.
 */
appModule.controller("appCtrl",["$scope",'$window','$http',function($scope,$window,$http){
    $scope.viewTabs=[
        {tabName:"用户中心",tabUrl:"#userCenter"}
    ];
    $scope.selectedTab=1;

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
        var leaf = menu.leaf==null?true:menu.leaf;
        if(!leaf){
            return;
        }

        $scope.curItemId=menu.id;

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

    $scope.navItems = [{id:1,name:"用户中心",link:"#userCenter",icon:"icon-user",leaf:true},
        {id:2,name:"用户管理",link:"#",icon:"icon-desktop",leaf:false,
            children:[{id:3,name:"用户列表",icon:"icon-double-angle-right",link:"#userList",leaf:true},
                {id:4,name:"权限管理",icon:"icon-double-angle-right",link:"#authorizationMgt",leaf:true}]},
        {id:9,name:"系统管理",icon:"icon-edit",link:"#",leaf:false,
            children:[{id:10,name:"导航栏管理",icon:"icon-double-angle-right",link:"#navMangt",leaf:true}]},
        {id:5,name:"导航栏事例",link:"#",icon:"icon-search",leaf:false,
            children:[{id:6,name:"第一级菜单",icon:"icon-double-angle-right",link:"#",leaf:false,
                         children:[{id:7,name:"第二级菜单",icon:"icon-leaf",link:"#",leaf:true},
                             {id:8,name:"第二级结点",icon:"icon-pencil",link:"#",leaf:true}]
            }]
        }
    ];

    var refreshList = function () {
        $http({
            method: "POST",
            url: "menu/hierachyList"
        }).success(function (data) {
            $scope.navItems = data;
        });
    };

    refreshList();

    $scope.notificationItems = [{type:"中奖",num:88},
        {type:"吃饭",num:3}];
}]);