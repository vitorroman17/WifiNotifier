# WifiNotifier

Aplicativo Android que detecta conexões e desconexões de Wi-Fi e envia notificações.

## Funcionalidades

- Detecta automaticamente quando o dispositivo se conecta ou desconecta de uma rede Wi-Fi  
- Funciona mesmo após reinício do dispositivo  
- Exibe notificações persistentes  
- Mostra um histórico de logs diretamente na tela

## Instalação

1. Clone o repositório  
2. Abra no Android Studio  
3. Execute via emulador ou dispositivo real

## Permissões necessárias

Via ADB:

```bash
adb shell pm grant com.example.wifinotifier android.permission.POST_NOTIFICATIONS
adb shell pm grant com.example.wifinotifier android.permission.ACCESS_FINE_LOCATION
adb shell pm grant com.example.wifinotifier android.permission.NEARBY_WIFI_DEVICES
adb shell pm grant com.example.wifinotifier android.permission.ACCESS_WIFI_STATE
adb shell pm grant com.example.wifinotifier android.permission.ACCESS_NETWORK_STATE
adb shell pm grant com.example.wifinotifier android.permission.RECEIVE_BOOT_COMPLETED
```

## Configuração do Android SDK

Crie um arquivo `local.properties` na raiz do projeto com o seguinte conteúdo (ajuste o caminho se necessário):

```
sdk.dir=C:\\Users\\vitor\\AppData\\Local\\Android\\Sdk
```

## Licença

MIT — veja o arquivo LICENSE para mais detalhes.


## Configuração do SDK
Crie um arquivo `local.properties` na raiz do projeto com o caminho do Android SDK:
```
sdk.dir=C:\\Users\\SeuUsuario\\AppData\\Local\\Android\\Sdk
```
Você pode usar o arquivo `local.properties.example` como base.