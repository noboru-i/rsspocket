@Rss =
    add: (index) ->
        return if $('#like_' + index + ' .addButton').hasClass('disabled')

        data = [@_getParam index]
        that = this
        callback = (data) ->
            that._disableAddButton index
        $.post '/api/rss/add', JSON.stringify(data), callback

    builkAdd: () ->
        that = this
        checkedItems = $.makeArray $('table .check_regist:checked').map () ->
            that._getParam $(this).data('id')
        callback = (data) ->
            for item in checkedItems
                that._disableAddButton item.index
        $.post '/api/rss/add', JSON.stringify(checkedItems), callback

    edit: (index) ->
        data = [@_getParam index]
        callback = (data) ->
            console.log data
        $.post '/api/rss/edit', JSON.stringify(data), callback

    delete: (index) ->
        data = [@_getParam index]
        callback = (data) ->
            console.log data
            $('#like_' + index).remove()
        $.post '/api/rss/delete', JSON.stringify(data), callback

    _getParam: (index) ->
        tags = $.makeArray $('.tag_'+index)
                .filter(() -> return $(this).val() != '')
                .map(() -> return $(this).val())
        data =
            index: index,
            url: $('#url_' + index).text(),
            tags: tags
        data

    _disableAddButton: (index) ->
        $('#like_' + index + ' .addButton')
            .addClass('disabled')
            .removeClass('btn-success')

    openFeed: (element) ->
        url = $(element).data('url')
        data = 
            url: url
        callback = (data) ->
            results = data.results
            $('.feedList').empty()
            createList = (row) ->
                $link = $('<a />').attr(
                    'href': row.link
                    'target': '_blank'
                    ).addClass('btn').addClass('btn-link')
                    .append(row.title)
                $button = $('<a />')
                $row = $('<tr />')
                    .append($('<td />').append($link))
                    .append($('<td />').append($button))
                $('.feedList').append($row);
            createList(row) for row in results
        $.get '/api/rss/feed', data, callback
