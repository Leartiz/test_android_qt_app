import QtQuick
import QtQuick.Controls

import AndroidToast 1.0
import AndroidBannerAd 1.0

Window {
    width: 640
    height: 480
    visible: true
    title: qsTr("Hello World")

    AndroidBannerAd {
        id: _androidBannerAd

        Component.onCompleted: {
            _androidBannerAd.adUnitId = "demo-banner-yandex";
            _androidBannerAd.position = Qt.point(20, 1500)
            _androidBannerAd.size = Qt.size(320, 50)

            console.log("AndroidBannerAd completed!");
        }
    }

    Rectangle {
        visible: true
        anchors.fill: parent
        color: "red"

        Column {
            anchors.centerIn: parent
            width: 250
            spacing: 25

            Button {
                width: parent.width
                text: "Show Toast"
                onClicked: {
                    AndroidToast.showToast("Это сообщение Toast через JNI!")
                }
            }

            Button {
                width: parent.width
                text: "Show Banner"
                onClicked: {
                    _androidBannerAd.show();
                }
            }

            Button {
                width: parent.width
                text: "Hide Banner"
                onClicked: {
                    _androidBannerAd.hide();
                }
            }
        }
    }

    Component.onCompleted: {
        console.log("Completed Running!")
    }
}
