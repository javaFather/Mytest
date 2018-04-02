$(document).ready(function($){
    passTable();
});




function passTable() {
    $("#exampleTableEvents").bootstrapTable({
        url:ctx + '/main/userData',           //请求后台的URL（*）
        // queryParams : pas_queryParams,                  //查询参数
        pagination:true,                                //是否分页
        pageNumber:1,                                   //初始化加载第一页，默认第一页
        pageSize: 5,                                    //每页的记录行数（*）
        pageList: [5, 6, 7, 20],                        //可供选择的每页的行数（*）
        cache: false,                                   //缓存
        striped: true,                                  //是否显示行间隔色
        // sidePagination: 'server',                       //分页方式：client客户端分页，server服务端分页
        clickToSelect: true,                            //是否启用点击选中行
        columns: [[{
            field: 'bookId',
            title: '借款编号',
        }, {
            field: 'name',
            title: '进件营业部',
        }, {
            field: 'number',
            title: '提交时间',
        }
        ]]
    });
}