# WifiNotifier

Aplicativo Android que detecta conexões e desconexões de Wi-Fi e envia notificações.

## Funcionalidades

- Detecta automaticamente quando o dispositivo se conecta ou desconecta de uma rede Wi-Fi
- Funciona mesmo após reinício do dispositivo
- Exibe notificações persistentes

## Instalação

1. Clone o repositório
2. Abra no Android Studio
3. Execute via emulador ou dispositivo real

## Permissões necessárias

Via ADB:

```
adb shell pm grant com.example.wifinotifier android.permission.POST_NOTIFICATIONS
adb shell pm grant com.example.wifinotifier android.permission.ACCESS_FINE_LOCATION
adb shell pm grant com.example.wifinotifier android.permission.ACCESS_WIFI_STATE
adb shell pm grant com.example.wifinotifier android.permission.ACCESS_NETWORK_STATE
adb shell pm grant com.example.wifinotifier android.permission.RECEIVE_BOOT_COMPLETED
```

## Contribuindo

Pull requests são bem-vindos. Para mudanças maiores, abra uma issue antes para discutir o que você gostaria de modificar.

## Licença

MIT — veja o arquivo LICENSE para mais detalhes.

### Configuração do Android SDK

Crie um arquivo `local.properties` na raiz do projeto com o conteúdo:

