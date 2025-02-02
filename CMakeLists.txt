cmake_minimum_required(VERSION 3.16)

project(test_android_qt_app VERSION 0.1 LANGUAGES CXX)

set(CMAKE_CXX_STANDARD_REQUIRED ON)

find_package(Qt6 REQUIRED COMPONENTS
    Quick)

qt_standard_project_setup(REQUIRES 6.8)

qt_add_executable(apptest_android_qt_app
    main.cpp
)

qt_add_qml_module(apptest_android_qt_app
    URI test_android_qt_app
    VERSION 1.0
    QML_FILES
        Main.qml
)

# Qt for iOS sets MACOSX_BUNDLE_GUI_IDENTIFIER automatically since Qt 6.1.
# If you are developing for iOS or macOS you should consider setting an
# explicit, fixed bundle identifier manually though.
set_target_properties(apptest_android_qt_app PROPERTIES
#    MACOSX_BUNDLE_GUI_IDENTIFIER com.example.apptest_android_qt_app
    MACOSX_BUNDLE_BUNDLE_VERSION ${PROJECT_VERSION}
    MACOSX_BUNDLE_SHORT_VERSION_STRING ${PROJECT_VERSION_MAJOR}.${PROJECT_VERSION_MINOR}
    MACOSX_BUNDLE TRUE
    WIN32_EXECUTABLE TRUE

    QT_ANDROID_PACKAGE_SOURCE_DIR ${CMAKE_SOURCE_DIR}/android
)

target_link_libraries(apptest_android_qt_app PRIVATE
    Qt6::Quick
)

include(GNUInstallDirs)
install(TARGETS apptest_android_qt_app
    BUNDLE DESTINATION .
    LIBRARY DESTINATION ${CMAKE_INSTALL_LIBDIR}
    RUNTIME DESTINATION ${CMAKE_INSTALL_BINDIR}
)
