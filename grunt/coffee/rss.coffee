@Rss =
    add: (index) ->
        tags = $.makeArray $('.tag_'+index).map(() -> return ($(this).val()))
        data =
            url: $('#url_' + index).text(),
            tags: tags
        console.log data
        callback = (data) ->
            console.log data
        $.post '/api/rss/add', JSON.stringify(data), callback

