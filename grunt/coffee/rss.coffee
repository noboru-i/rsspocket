@Rss =
    add: (index) ->
        data = @_getParam index
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
        $.post '/api/rss/builkadd', JSON.stringify(data), callback

    _getParam: (index) ->
        tags = $.makeArray $('.tag_'+index).map(() -> return ($(this).val()))
        data =
            url: $('#url_' + index).text(),
            tags: tags
        data

