#ifndef ANDROIDBANNERAD_H
#define ANDROIDBANNERAD_H

#include <QObject>

class AndroidBannerAd : public QObject
{
    Q_OBJECT
public:
    explicit AndroidBannerAd(QObject *parent = nullptr);

signals:

};

#endif // ANDROIDBANNERAD_H
