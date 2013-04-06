module.exports = (grunt) ->
    grunt.loadNpmTasks 'grunt-contrib'

    grunt.initConfig
        watch:
            compass:
                files: "sass/**/*.sass",
                tasks: ["compass"]

        compass:
            dist:
                options:
                    sassDir: 'sass',
                    cssDir: '../war/css'

    grunt.registerTask 'default', ['compass']
            
