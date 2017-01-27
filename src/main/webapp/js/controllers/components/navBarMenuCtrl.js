/**
 * Created by LINSHIRUI447 on 2016-09-20.
 */
appModule.controller("navBarMenuCtrl",["$scope","$rootScope",function($scope,$rootScope){
    //监听路由变化
    $scope.$on("$routeChangeSuccess",function(event, current, previous) {
        var currentPath = current.$$route.originalPath;
        var slashStart = currentPath.indexOf("/");
        $scope.currentPath="#"+currentPath.substr(slashStart+1);
        console.log($scope.currentPath);
        activeNavItem($scope.currentPath,$scope.navItems);
    });

    //改变导航栏选中状态
    //递归遍历导航栏，并向上传返回选中结果
    function activeNavItem(currentPath,navItems) {
        if(_.isNull(navItems) || navItems.length==0){
            return false;
        }
        for(var i=0;i<navItems.length;i++){
            var navItem = navItems[i];
            recursiveActiveItems(currentPath,navItem);
        }
    }

    function recursiveActiveItems(currentPath,navItem){
        if(_.isEqual(navItem.href,currentPath)){
            navItem.selected = true;
            return true;
        }else{
            if(currentPath,navItem.hasChild){
                var hasChildSelected = false;
                for(var i=0;i<navItem.children.length;i++){
                    var childSelected = recursiveActiveItems(currentPath,navItem.children[i]);
                    if(childSelected){
                        hasChildSelected = true;
                    }
                }
                navItem.selected=hasChildSelected;
                return hasChildSelected;
            }else{
                navItem.selected=false;
                return false;
            }
        }
    }

}]);