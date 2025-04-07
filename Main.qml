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
            _androidBannerAd.size = Qt.size(320, 50) // dp
            _androidBannerAd.placeAtBottomCenter();  // quick alignment

            console.log("AndroidBannerAd completed!");
            console.log("AndroidBannerAd is visible: " +
                        _androidBannerAd.isVisible);
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
                    //_androidBannerAd.show();
                    _androidBannerAd.isVisible = true;
                }
            }

            Button {
                width: parent.width
                text: "Hide Banner"
                onClicked: {
                    //_androidBannerAd.hide();
                    _androidBannerAd.isVisible = false;
                }
            }
        }
    }

    Component.onCompleted: {
        console.log("Completed Running!")
    }
}
