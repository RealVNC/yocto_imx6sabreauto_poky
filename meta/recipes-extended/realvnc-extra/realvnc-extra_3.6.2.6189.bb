# Copyright (C) 2017 RealVNC Ltd.  All Rights Reserved.

LICENSE = "CLOSED"

REALVNC_ARCHIVE_NAME="RealVNC-ViewerSDK-${PV}-Linux-automotive"

SRC_URI = "file://${REALVNC_ARCHIVE_NAME}.tgz"

S_ARCHIVE = "${WORKDIR}/${REALVNC_ARCHIVE_NAME}"

FILES_${PN} = "/"

do_install() {
    install -d ${D}/usr/bin
    install -m 0755 ${S_ARCHIVE}/wifip2padaptations/vncwifip2padaptation-wpa_supplicant/scripts/isc/wpa_dhcp_server ${D}/usr/bin/
    install -m 0755 ${S_ARCHIVE}/wifip2padaptations/vncwifip2padaptation-wpa_supplicant/scripts/isc/wpa_dhcp_client ${D}/usr/bin/
    install -d ${D}/etc/udev/rules.d
    install -m 0755 ${S_ARCHIVE}/discoverysdk/UdevScripts/etc/udev/linux_dhcp_setup.sh ${D}/etc/udev/
    install -m 0755 ${S_ARCHIVE}/wifip2padaptations/vncwifip2padaptation-wpa_supplicant/scripts/udev/etc/udev/wfd_wpa_supplicant_setup.sh ${D}/etc/udev/
    install -m 0644 ${S_ARCHIVE}/discoverysdk/UdevScripts/etc/udev/rules.d/99-mirrorlink.rules ${D}/etc/udev/rules.d/
    install -m 0644 ${S_ARCHIVE}/discoverysdk/UdevScripts/etc/udev/rules.d/99-usbdiscoverer.rules ${D}/etc/udev/rules.d/
    install -m 0644 ${S_ARCHIVE}/wifip2padaptations/vncwifip2padaptation-wpa_supplicant/scripts/udev/etc/udev/rules.d/99-wfd.rules ${D}/etc/udev/rules.d/

	mkdir -p ${D}/etc/modprobe.d
	cat >> ${D}/etc/modprobe.d/ath6kl_core.conf << EOF
# Load ath6kl_core with WiFi P2P enabled.
options ath6kl_core ar6k_clock=26000000 ath6kl_p2p=1 wow_mode=2
EOF
	chmod 0644 ${D}/etc/modprobe.d/ath6kl_core.conf
	mkdir -p ${D}/etc/modules-load.d 
    cat >> ${D}/etc/modules-load.d/libcomposite.conf << EOF
# Load libcomposite to support USB configfs
libcomposite
EOF
    chmod 0644 ${D}/etc/modules-load.d/libcomposite.conf
}

