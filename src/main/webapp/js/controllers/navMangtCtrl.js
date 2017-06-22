/**
 * Created by LINSHIRUI447 on 2016-09-04.
 */
appModule.controller("navMangtCtrl",["$scope","$http","ngDialog",function($scope,$http,ngDialog) {


    $scope.menuTypes = [{id: 0, type_key: 'nav', type_name: '导航栏'},
        {id: 1, type_key: 'menu', type_name: '菜单'}];

    $scope.clickToOpen = function () {
        $scope.navOperationTypeText = "创建";
        $scope.parentId = '0';
        var createNavDialog = ngDialog.open({
            template: 'views/dialogs/saveOrUpdateNavMangtDialog.html',
            scope: $scope,
            className: 'ngdialog-theme-plain',
            closeByDocument: false,
            controller: ['$scope', function ($scope) {
                $scope.sendCreateMenu = function () {
                    var menuInsert = {};
                    menuInsert.name = $scope.navName;
                    menuInsert.icon = $scope.navIcon;
                    menuInsert.link = $scope.navLink;
                    menuInsert.order = $scope.navOrder;
                    menuInsert.type = $scope.menuType.type_name;
                    menuInsert.parentId = $scope.parentId;
                    $http({
                        method: "POST",
                        url: "menu/create",
                        data: menuInsert
                    }).success(function (data) {
                        if (data.rtnCode) {
                            $scope.closeThisDialog();
                            refreshList();
                        }
                    });
                };
            }]
        });
    };

    var appendItemDialog = function (parentId) {
        $scope.navOperationTypeText = "创建";
        var createNavDialog = ngDialog.open({
            template: 'views/dialogs/saveOrUpdateNavMangtDialog.html',
            scope: $scope,
            className: 'ngdialog-theme-plain',
            closeByDocument: false,
            controller: ['$scope', function ($scope) {
                $scope.parentId = parentId;
                $scope.sendCreateMenu = function () {
                    var menuInsert = {};
                    menuInsert.name = $scope.navName;
                    menuInsert.icon = $scope.navIcon;
                    menuInsert.link = $scope.navLink;
                    menuInsert.order = $scope.navOrder;
                    menuInsert.type = $scope.menuType.type_name;
                    menuInsert.parentId = $scope.parentId;
                    $http({
                        method: "POST",
                        url: "menu/create",
                        data: menuInsert
                    }).success(function (data) {
                        if (data.rtnCode) {
                            layer.msg("设置成功！");
                            $scope.closeThisDialog();
                            refreshList();
                        }
                    });
                };
            }]
        });
    };

    var updateItemDialog = function(item){

        $scope.navOperationTypeText = "创建";

        $scope.id = item.id;
        $scope.navName = item.name;
        $scope.navIcon = item.icon;
        $scope.navLink = item.link;
        $scope.navOrder = item.order;
        $scope.type = item.type;
        $scope.parentId = item.parentId;
        $scope.dataStatus = item.dataStatus;

        // var updateNavDialog = ngDialog.open({
        //     template: 'views/dialogs/saveOrUpdateNavMangtDialog.html',
        //     scope: $scope,
        //     className: 'ngdialog-theme-plain',
        //     closeByDocument: false,
        //     controller: ['$scope', function ($scope) {
        //         $scope.parentId = $scope.parentId;
        //         $scope.sendCreateMenu = function () {
        //             var menuInsert = {};
        //             menuInsert.id   = $scope.id;
        //             menuInsert.name = $scope.navName;
        //             menuInsert.icon = $scope.navIcon;
        //             menuInsert.link = $scope.navLink;
        //             menuInsert.order =$scope.navOrder;
        //             menuInsert.type = $scope.type;
        //             menuInsert.parentId = $scope.parentId;
        //             $http({
        //                 method: "POST",
        //                 url: "menu/update",
        //                 data: menuInsert
        //             }).success(function (data) {
        //                 if (data.rtnCode) {
        //                     $scope.closeThisDialog();
        //                     refreshList();
        //                 }
        //             });
        //         };
        //     }]
        // });
    };

    var refreshList = function () {
        $http({
            method: "POST",
            url: "menu/hierachyList"
        }).success(function (data) {
            $scope.menuList = data;
            $scope.paginationConf.totalItems = data.length;
            $scope.paginationConf.currentPage = 1;
        });
    };

    refreshList();

    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 8000,
        itemsPerPage: 15,
        pagesLength: 15,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function () {

        }
    }

    $scope.removeItem = function(scope){
        layer.confirm('确定要删除当前节点？', {
            btn: ['删除','取消'], //按钮
            title: '操作确认'
        }, function(index){
            scope.remove();
            layer.close(index);
        });
    }

    $scope.appendItem =function(scope){
        console.log(scope.$modelValue.id);
        appendItemDialog(scope.$modelValue.id);
    }

    $scope.updateItem = function(scope){
        updateItemDialog(scope.$modelValue);
    }

    $scope.updateMenu = function () {
                    var menuInsert = {};
                    menuInsert.id   = $scope.id;
                    menuInsert.name = $scope.navName;
                    menuInsert.icon = $scope.navIcon;
                    menuInsert.link = $scope.navLink;
                    menuInsert.order =$scope.navOrder;
                    menuInsert.type = $scope.type;
                    menuInsert.dataStatus = $scope.dataStatus;
                    menuInsert.parentId = $scope.parentId;
                    $http({
                        method: "POST",
                        url: "menu/update",
                        data: menuInsert
                    }).success(function (data) {
                        if (data.rtnCode=="000") {
                            layer.msg("设置成功！");
                            refreshList();
                        }
                    });
     };

    $scope.forwardItem =function(scope){
        console.log(scope.$modelValue.id);
        var param={};
        var item = scope.$modelValue;
        param.id = item.id;
        param.direction = "forward";
        $http({
            method:"POST",
            url:"menu/changeOrder",
            data:param
        }).success(function(data){
            if(data.rtnCode=="000"){
                layer.msg("设置成功！");
                refreshList();
            }
        });
    };

    $scope.backwardItem =function(scope){
        console.log(scope.$modelValue.id);
        var param={};
        var item = scope.$modelValue;
        param.id = item.id;
        param.direction = "backward";
        $http({
            method:"POST",
            url:"menu/changeOrder",
            data:param
        }).success(function(data){
            if(data.rtnCode=="000"){
                layer.msg("设置成功！");
                refreshList();
            }
        });
    };

    $scope.collapseAll = function () {
        $scope.$broadcast('angular-ui-tree:collapse-all');
    };

    $scope.expandAll = function () {
        $scope.$broadcast('angular-ui-tree:expand-all');
    };

    $scope.treeOptions = {
        accept: function(sourceNodeScope, destNodesScope, destIndex) {
            return true;
        },
        removed: function (node){
            console.log(node.$modelValue.id+" has been removed");
            var param = {};
            param.id = node.$modelValue.id;
            param.isResursively = "N";
            $http({
                method:"POST",
                url:"menu/deleteMenuById",
                data:param
            }).success(function(data){
                if(data.rtnCode=="000"){
                    layer.msg("设置成功！");
                    refreshList();
                }
            });
        }
    }

}]);

