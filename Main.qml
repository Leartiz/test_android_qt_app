import QtQuick
import QtQuick.Controls

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
            onClicked: Qt.quit()
        }
    }

    Component.onCompleted: {
        console.log("Completed Running!")
    }
}
