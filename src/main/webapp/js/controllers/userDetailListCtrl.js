/**
 * Created by LINSHIRUI447 on 2016-09-04.
 */
/**
 * Created by LINSHIRUI447 on 2016-09-04.
 */
appModule.controller("userListCtrl",["$scope","$http","ngDialog",
    function($scope,$http,ngDialog){

    var getList = function(){

        var pageConfig = {};
        pageConfig.pageNum = $scope.paginationConf.currentPage;
        pageConfig.pageSize = $scope.paginationConf.itemsPerPage;
        $http({
            method:"POST",
            url:"user/list",
            data:pageConfig
        }).success(function(data){
            $scope.userList=data.list;
            $scope.paginationConf.totalItems = data.pageSummary.recordCount;
        });
    };

    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 0,
        itemsPerPage: 10,
        pagesLength: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function () {
            getList();
        }
    }

    $scope.showCreateUserDialog = function(){
        $scope.userOperationTypeText = "创建";
        $scope.nickname = "";
        $scope.state = "";
        var createUserDialog = ngDialog.open({
            template: 'views/dialogs/saveOrUpdateUserDialog.html',
            scope:$scope,
            className: 'ngdialog-theme-plain',
            closeByDocument: false,
            controller:['$scope', function ($scope) {
                $scope.saveOrUpdateUser = function () {
                    var userInsert = {};
                    userInsert.nickname = $scope.nickname;
                    userInsert.state = $scope.state;
                    $http({
                        method: "POST",
                        url: "user/save",
                        data: userInsert
                    }).success(function (data) {
                        $scope.closeThisDialog();
                        getList();
                    });
                };
            }]
        });
    };

        $scope.showUpdateUserDialog = function(item){
            $scope.userOperationTypeText = "编辑";
            $scope.nickname = item.nickname;
            $scope.state = item.state;
            var updateUserDialog = ngDialog.open({
                template: 'views/dialogs/saveOrUpdateUserDialog.html',
                scope:$scope,
                className: 'ngdialog-theme-plain',
                closeByDocument: false,
                controller:['$scope', function ($scope) {
                    $scope.saveOrUpdateUser = function () {
                        var userUpdate = {};
                        userUpdate.id = item.id;
                        userUpdate.nickname = $scope.nickname;
                        userUpdate.state = $scope.state;
                        $http({
                            method: "POST",
                            url: "user/update",
                            data: userUpdate
                        }).success(function (data) {
                            $scope.closeThisDialog();
                            getList();
                        });
                    };
                }]
            });
        };

    $scope.deleteUser = function(item){
        layer.confirm('确定要删除当前用户？', {
            btn: ['删除','取消'], //按钮
            title: '操作确认'
        }, function(index){
            var param = {};
            param.id = item.id;
            $http({
                method:"POST",
                url:"user/delete",
                data:param
            }).success(function(data){
                getList();
                layer.close(index);
            });
        });
    }
}]);

