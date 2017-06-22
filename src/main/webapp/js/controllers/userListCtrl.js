/**
 * Created by LINSHIRUI447 on 2016-09-04.
 */
/**
 * Created by LINSHIRUI447 on 2016-09-04.
 */
appModule.controller("userListCtrl",["$scope","$http",function($scope,$http){

    var getList = function(){
        $http({
            method:"POST",
            url:"user/list"
        }).success(function(data){
            $scope.userList=data;
            $scope.currentPage = 2;
            $scope.paginationConf.totalItems = data.length+20;
            $scope.paginationConf.currentPage =2;
        });
    };

    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 8000,
        itemsPerPage: 15,
        pagesLength: 15,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function () {
            getList();
        }
    }
}]);

