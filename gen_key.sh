keytool -genkey -v -alias Rsspocket -keystore rsspocket -keyalg RSA -sigalg SHA1withRSA -dname "CN=rsspocket.appspot.com, OU=noboru, O=noboru, L=Nakahara-ku, ST=Kanagawa, C=JP" -storepass XXX -keypass XXX

keytool -export -rfc -keystore rsspocket -storepass XXX -alias Rsspocket -file mycert.pem

mv rsspocket war/rsspocket/