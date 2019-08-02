/*==================================================================================
    Custom JS (Any custom js code you want to apply should be defined here).
====================================================================================*/
/**
 * form表单提交本页面打开
 * @param url
 * @param params
 */
function postCurrent(url, params) {
    var form = $("<form method='post'></form>");
    var input;
    form.attr({"action": url});
    if (params) {
        $.each(params, function (key, value) {
            input = $("<input type='hidden'>");
            input.attr({"name": key});
            input.val(value);
            form.append(input);
        });
    }
    $(document.body).append(form);
    form.submit();
}

function getDetail(id) {
    var params = {
        blogId: id
    }
    postCurrent('/blog/detail', params)
}

function getLabel(id) {
    var params = {
        tabId: id
    }
    postCurrent('/blog/label', params)
}

function getURL(url, id) {
    var params = {
        tabId: id
    }
    postCurrent(url, params)
}

function getPage(page) {
    var url = $("#url").val();
    var paramsId = $("#paramsId").val();
    var arr = paramsId.split("=");
    var params = {
        page: page,
    }
    params[arr[0]] = arr[1];
    postCurrent(url, params)
}

function labSelect() {
    var labelId = $("#sonLabelSelected").val();
    $.post('/blog/getClassificationArticles',
        {
            labelId: labelId
        }, function (data) {
            var classificationArticles = data.data;
            var content = '';
            for (var i in classificationArticles) {
                content += '<div class="wrp-cover">';
                content += '<div class="post-thumb">';
                content += '<a onclick="getDetail(' + classificationArticles[i].id + ')">';
                content += '<img src="../images/sidebar/rp-1.jpg" alt="" class="img-fluid">';
                content += '</a>';
                content += '</div>';
                content += '<div class="post-title">';
                content += '<a onclick="getDetail(' + classificationArticles[i].id + ')">' + classificationArticles[i].title + '</a>';
                content += '</div>';
                content += '</div>';
            }
            $("#classificationArticlesContent").html(content)
        })

}


$("#subForm").on('submit', function (e) {
    e.preventDefault();
    var email = $("#email").val();
    if (!email) {
        layer.msg('请输入邮箱!');
        return false;
    } else {
        if (/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(email) == false) {
            layer.msg('请输入正确的邮箱!');
            return false;
        }
    }
    $.post('/blog/subscribe',
        {
            email: email
        }, function (data) {
            if (data.code == 0) {
                layer.msg(data.msg, function () {
                    $("#email").val("");
                    layer.closeAll();
                })
            } else {
                layer.msg(data.msg, function () {
                    layer.closeAll();
                })
            }
        })
})

$("#leaveForm").on('submit', function (e) {
    e.preventDefault();
    var name = $("#userName").val();
    var email = $("#userEmail").val();
    var message = $("#userMessage").val();
    if (!name || !email || !message) {
        if(!name){
            layer.msg('请输入你的名字!');
            return false;
        }else if(!email){
            layer.msg('请输入邮箱!');
            return false;
        }else{
            layer.msg('请输入留言!');
            return false;
        }
    } else {
        if (/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(email) == false) {
            layer.msg('请输入正确的邮箱!');
            return false;
        }
    }
    $.post('/blog/leaveAMessage',
        {
            name: name,
            email: email,
            message: message,
        }, function (data) {
            if (data.code == 0) {
                layer.msg(data.msg, function () {
                    $("#userName").val("");
                    $("#userEmail").val("");
                    $("#userMessage").val("");
                    layer.closeAll();
                })
            } else {
                layer.msg(data.msg, function () {
                    layer.closeAll();
                })
            }
        })
})
