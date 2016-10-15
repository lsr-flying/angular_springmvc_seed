/**
 * Created by LINSHIRUI447 on 2016-09-12.
 */
appModule.service("HelloWorldService",function(){
    return {
        greeting:function(name){
            return "[From HelloWorldService]Hello,"+name;
        }
    };
})