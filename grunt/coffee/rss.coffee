@Rss =
    add: (index) ->
        data = [@_getParam index]
        console.log data
        callback = (data) ->
            console.log data
        $.post '/api/rss/add', JSON.stringify(data), callback

    builkAdd: () ->
        that = this
        data = $.makeArray $('table .check_regist:checked').map () ->
            that._getParam $(this).data('id')
        console.log data
        callback = (data) ->
            console.log data
        $.post '/api/rss/add', JSON.stringify(data), callback

    edit: (index) ->
        data = [@_getParam index]
        callback = (data) ->
            console.log data
        $.post '/api/rss/edit', JSON.stringify(data), callback

    delete: (index) ->
        data = [@_getParam index]
        callback = (data) ->
            console.log data
        $.post '/api/rss/delete', JSON.stringify(data), callback

    _getParam: (index) ->
        tags = $.makeArray $('.tag_'+index)
                .filter(() -> return $(this).val() != '')
                .map(() -> return $(this).val())
        data =
            url: $('#url_' + index).text(),
            tags: tags
        data

