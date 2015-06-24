# Copyright (C) 2014-2015 RealVNC Ltd.  All Rights Reserved.

LICENSE = "CLOSED"

REALVNC_BUILD_NUMBER="1234"
REALVNC_ARCHIVE_NAME="RealVNC-ViewerSDK-3.2.1.${REALVNC_BUILD_NUMBER}-Linux-automotive"

SRC_URI = "file://${REALVNC_ARCHIVE_NAME}.tgz"

S_FIRMWARE = "${WORKDIR}"
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
}

