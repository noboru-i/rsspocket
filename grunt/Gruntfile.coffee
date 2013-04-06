module.exports = (grunt) ->
    grunt.loadNpmTasks 'grunt-contrib'

    grunt.initConfig
        watch:
            compass:
                files: 'sass/**/*.sass',
                tasks: ['compass']
            copy:
                files: 'css/**/*.css',
                tasks: ['copy']
            coffee:
                files: 'coffee/**/*.coffee',
                tasks: ['coffee']

        compass:
            dist:
                options:
                    sassDir: 'sass',
                    cssDir: '../war/css'

        copy:
            main:
                files: [
                    {src: ['css/*'], dest: '../war/', filter: 'isFile'},
                    {src: ['js/*'], dest: '../war/', filter: 'isFile'}
                ]

        coffee:
            compile:
                files:
                    '../war/js/app.js': ['coffee/**/*.coffee']

    grunt.registerTask 'default', ['compass', 'copy', 'coffee']

