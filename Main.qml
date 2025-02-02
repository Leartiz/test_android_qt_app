import QtQuick
import QtQuick.Controls
import AndroidToast 1.0

Window {
    width: 640
    height: 480
    visible: true
    title: qsTr("Hello World")

    Rectangle {
        visible: true
        anchors.fill: parent
        color: "red"

        Button {
            anchors.centerIn: parent
            text: "Exit"
            onClicked: {
                AndroidToast.showToast("Это сообщение Toast через JNI!")
            }
        }
    }

    Component.onCompleted: {
        console.log("Completed Running!")
    }
}
