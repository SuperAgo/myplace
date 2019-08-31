/*==================================================================================
    Custom JS (Any custom js code you want to apply should be defined here).
====================================================================================*/
/**
 * form表单提交本页面打开
 * @param url
 * @param params
 */
function postCurrent(url, params) {
    var form = $("<form method='post' target='my-iframe'></form>");
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
    localStorage.setItem("url", url);
    localStorage.setItem("params", JSON.stringify(params));
    form.submit();
}

function getDetail(id) {
    var params = {
        blogId: id
    }
    postCurrent('/free/detail', params)
}

function getLabel(id) {
    var params = {
        tabId: id
    }
    postCurrent('/free/label', params)
}

function getURL(url, id) {
    var params = {
        tabId: id
    }
    postCurrent(url, params)
}

function getTrueURL(url, id) {
    var params = {
        tabId: id
    }
    localStorage.setItem("url", url);
    localStorage.setItem("params", JSON.stringify(params));
    window.location.href = '/free/blog';
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
    $.post('/free/getClassificationArticles',
        {
            labelId: labelId
        }, function (data) {
            var classificationArticles = data.data;
            var content = '';
            for (var i in classificationArticles) {
                content += '<div class="wrp-cover">';
                content += '<div class="post-thumb">';
                content += '<a onclick="getDetail(' + classificationArticles[i].id + ')">';
                content += '<img src="'+classificationArticles[i].banner+'" alt="" class="img-fluid">';
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
    $.post('/free/subscribe',
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
    var parentId = $("#parentId").val();
    var page = $(".current").html();
    if (!name || !email || !message) {
        if (!name) {
            layer.msg('请输入你的名字!');
            return false;
        } else if (!email) {
            layer.msg('请输入邮箱!');
            return false;
        } else {
            layer.msg('请输入留言!');
            return false;
        }
    } else {
        if (/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(email) == false) {
            layer.msg('请输入正确的邮箱!');
            return false;
        }
    }
    $.ajax({
        type: 'POST',
        url: '/free/leaveAMessage',
        data: {
            params:
                {
                    name: name,
                    parentId: parentId,
                    email: email,
                    message: message
                }
        },
        async: false,
        success: function (data) {
            if (data.code == 0) {
                $.ajax({
                    type: 'POST',
                    url: '/free/getNewContact',
                    data: {
                        page: page
                    },
                    async: false,
                    success: function (data) {
                        $(".contact-cover").html(data);
                        $("#userName").val("");
                        $("#userEmail").val("");
                        $("#userMessage").val("");
                        $("#parentId").val("");
                    },
                });
                var msg = data.msg;
                if (parentId) {
                    msg = "回复成功！";
                }
                layer.msg(msg, function () {
                    layer.closeAll();
                })
            } else {
                layer.msg(data.msg, function () {
                    layer.closeAll();
                })
            }
        },
    });
})

function reply(mid) {
    $("#parentId").val(mid);
}

$("#commontForm").on('submit', function (e) {
    e.preventDefault();
    var name = $("#userName").val();
    var email = $("#userEmail").val();
    var message = $("#userMessage").val();
    var blogId = $("#blogId").val();
    var parentId = $("#parentId").val();
    if (!name || !email || !message) {
        if (!name) {
            layer.msg('请输入你的名字!');
            return false;
        } else if (!email) {
            layer.msg('请输入邮箱!');
            return false;
        } else {
            layer.msg('请输入留言!');
            return false;
        }
    } else {
        if (/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(email) == false) {
            layer.msg('请输入正确的邮箱!');
            return false;
        }
    }
    $.ajax({
        type: 'POST',
        url: '/free/setCommont',
        data: {
            params:
                {
                    name: name,
                    blogId: blogId,
                    parentId: parentId,
                    email: email,
                    message: message
                }
        },
        async: false,
        success: function (data) {
            if (data.code == 0) {
                $.ajax({
                    type: 'POST',
                    url: '/free/getNewCommont',
                    data: {
                        blogId: blogId
                    },
                    async: false,
                    success: function (data) {
                        $("#needRefesh").html(data);
                        $(".btn btn-comment").removeClass("collapsed");
                        $(".collapse").addClass("show");
                        $("#userName").val("");
                        $("#userEmail").val("");
                        $(" #userMessage").val("");
                        $("#parentId").val("");
                    },
                });
                layer.msg(data.msg, function () {
                    layer.closeAll();
                })
            } else {
                layer.msg(data.msg, function () {
                    layer.closeAll();
                })
            }
        },
    });

})

function hideImg(a) {
    $(a).hide();
}

$("#search").on('submit', function (e) {
    e.preventDefault();
    var title = $("#title").val();
    if (!title) {
        return false;
    }
    var params = {
        title: title
    }
    postCurrent('/free/search', params);
    $('.mobile-nav-menu .search-toggle').click();
})

$("#errorSearch").on('submit', function (e) {
    e.preventDefault();
    var title = $("#title").val();
    var url = "/free/search";
    var params = {
        title: title
    }
    if (!title) {
        url = '/free/blog';
    }
    localStorage.setItem("url", url);
    localStorage.setItem("params", JSON.stringify(params));
    window.location.href = '/free/blog';
})

$("#titleErrorSearch").on('submit', function (e) {
    e.preventDefault();
    var title = $("#title").val();
    var url = "/free/search";
    var params = {
        title: title
    }
    if (!title) {
        url = '/free/blog';
    }
    localStorage.setItem("url", url);
    localStorage.setItem("params", JSON.stringify(params));
    window.location.href = '/free/blog';
})
