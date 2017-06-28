/**
 * Created by LINSHIRUI447 on 2016-09-04.
 */
/**
 * Created by LINSHIRUI447 on 2016-09-04.
 */
appModule.controller("roleListCtrl",["$scope","$http","ngDialog",
    function($scope,$http,ngDialog){

    var getList = function(){
        $http({
            method:"POST",
            url:"role/getRoleList",
            data:{}
        }).success(function(data){
            $scope.roleList=data.data;
        });
    };

    $scope.search = function(){
      getList();
    };

    $scope.showCreateRoleDialog = function(){
        $scope.userOperationTypeText = "创建";
        var createRoleDialog = ngDialog.open({
            template: 'views/dialogs/saveOrUpdateRoleDialog.html',
            scope:$scope,
            className: 'ngdialog-theme-plain',
            closeByDocument: false,
            controller:['$scope', function ($scope) {
                $scope.saveOrUpdateRole = function () {
                    var roleInsert = {};
                    roleInsert.domainId = $scope.domainId;
                    roleInsert.roleKey = $scope.roleKey;
                    roleInsert.roleName = $scope.roleName;
                    roleInsert.createdBy = $scope.createdBy;
                    $http({
                        method: "POST",
                        url: "role/insertRole",
                        data: roleInsert
                    }).success(function (data) {
                        $scope.closeThisDialog();
                        getList();
                    });
                };
            }]
        });
    };

        $scope.showUpdateRoleDialog = function(item){
            $scope.roleOperationTypeText = "编辑";
            $scope.domainId = item.domainId;
            $scope.roleKey = item.roleKey;
            $scope.roleName = item.roleName;
            var updateRoleDialog = ngDialog.open({
                template: 'views/dialogs/saveOrUpdateRoleDialog.html',
                scope:$scope,
                className: 'ngdialog-theme-plain',
                closeByDocument: false,
                controller:['$scope', function ($scope) {
                    $scope.saveOrUpdateRole = function () {
                        var roleUpdate = {};
                        roleUpdate.id = item.id;
                        roleUpdate.domainId = $scope.domainId;
                        roleUpdate.roleKey = $scope.roleKey;
                        roleUpdate.roleName = $scope.roleName;
                        roleUpdate.updatedBy = "lsr";
                        $http({
                            method: "POST",
                            url: "role/updateRoleById",
                            data: roleUpdate
                        }).success(function (data) {
                            $scope.closeThisDialog();
                            getList();
                        });
                    };
                }]
            });
        };

    $scope.deleteRole = function(item){
        layer.confirm('确定要删除当前角色？', {
            btn: ['删除','取消'], //按钮
            title: '操作确认'
        }, function(index){
            var param = {};
            param.roleId = item.id;
            $http({
                method:"POST",
                url:"/role/deleteRoleById",
                data:param
            }).success(function(data){
                getList();
                layer.close(index);
            });
        });
    }
}]);

