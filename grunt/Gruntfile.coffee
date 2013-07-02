module.exports = (grunt) ->

  LIB_JS = [
    'libs/jquery/jquery.min.js'
    'libs/bootstrap/docs/assets/js/bootstrap.min.js'
    'libs/lodash/dist/lodash.compat.min.js'
    'libs/backbone/backbone-min.js'
    ]

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

    concat:
      dist:
        src: LIB_JS
        dest: '../war/js/libs.js'

    compass:
      lib:
        options:
          sassDir: 'libs/sass-bootstrap/lib',
          cssDir: '../war/css'
      dist:
        options:
          sassDir: 'sass',
          cssDir: '../war/css'

    coffee:
      compile:
        files:
          '../war/js/app.js': ['coffee/**/*.coffee']

    # package.jsonに定義されている'grunt-'を全て読み込む
    pkg = grunt.file.readJSON('package.json')
    for taskName, version of pkg.devDependencies
      grunt.loadNpmTasks taskName if taskName.indexOf('grunt-') == 0

  grunt.registerTask 'default', ['compass', 'concat', 'coffee']

