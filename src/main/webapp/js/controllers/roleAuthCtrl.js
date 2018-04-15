/**
 * Created by LINSHIRUI447 on 2016-09-04.
 */
appModule.controller("roleAuthCtrl",["$scope","$http","ngDialog",
    function($scope,$http,ngDialog){

    $scope.currentRole={};

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
    };

    $scope.getList = function(){
        $http({
            method:"POST",
            url:"role/getRoleList",
            data:{}
        }).success(function(data){
            $scope.roleList=data.data;
        });
    };


    $scope.getUserRoles = function(user){
        var qryParam = {};
        qryParam.userId = user.id;
        $http({
            method:"POST",
            url:"role/queryUserRole",
            data:qryParam
        }).success(function(data){
            var userRoles = data.data;
            for(var i=0;i<$scope.roleList.length;i++){
                $scope.roleList[i].isSelected = false;
                for(var j=0;j<userRoles.length;j++){
                    if($scope.roleList[i].id==userRoles[j].id){
                        $scope.roleList[i].isSelected = true;
                    }
                }
            }
        });


    };

    $scope.queryUserDetail = function(user){
        $scope.currentUser = user;
        $scope.getUserRoles(user);
    }

    $scope.saveOrUpdateUserRole = function(){
        var qryParam = {};
        qryParam.userId = $scope.currentUser.id;
        qryParam.roleIds = "";
        for(var i=0;i<$scope.roleList.length;i++){
            if($scope.roleList[i].isSelected){
                qryParam.roleIds =qryParam.roleIds+","+$scope.roleList[i].id;
            }
        }
        qryParam.createdBy="lsr";
        $http({
            method:"POST",
            url:"role/saveOrUpdateRole",
            data:qryParam
        }).success(function(data){
            layer.msg("执行成功");
        });
    };


    $scope.revertUserRole = function() {
        $scope.getUserRoles($scope.currentUser);
    }

        $scope.getList();

}]);

