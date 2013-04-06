module.exports = (grunt) ->
    grunt.loadNpmTasks 'grunt-contrib'

    grunt.initConfig
        watch:
            compass:
                files: 'sass/**/*.sass',
                tasks: ['compass']
            coffee:
                files: 'coffee/**/*.coffee',
                tasks: ['coffee']

        compass:
            dist:
                options:
                    sassDir: 'sass',
                    cssDir: '../war/css'

        coffee:
            compile:
                files:
                    '../war/js/app.js': ['coffee/**/*.coffee']

    grunt.registerTask 'default', ['compass', 'coffee']

