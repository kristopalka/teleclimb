import {View, Text, Image, TextInput, StyleSheet, TouchableOpacity} from "react-native";
import {styles} from "../styles";
import {useState} from "react";
import {ipAddressAndPort} from "../util/constants";
import axios from "axios";

export function Connector ({saveIp, navigation}) {

    const localStyles = StyleSheet.create({
        input: {
            borderBottomWidth: 1,
            marginLeft: 10,
            width: 100,
            marginTop: -12
        },
        button: {
            padding: 10,
            backgroundColor: "#555ec1",
            width: "40%",
            alignSelf: "center"
        },
        textButton: {
            color: "white",
            fontWeight: "bold",
            textAlign: "center",
        }
    })

    const [status, setStatus] = useState(false);
    const [ip, setIp] = useState("");
    let port = ":8080";

    function connectToSerive() {
        testService();
    }

    async function testService() {
        await axios
            .get("http://" + ip.trim() + port + "/test")
            .then(response => {
                if(response.status === 200) {
                    setStatus(true);
                    setIp(ip.trim());
                    saveIp(ip);
                }
            })
            .catch(error => console.log(error));
    }

    return (
        <View>
            <Text style={styles.boldSubtitle}>{status ? "Połączono poprawnie" : "Nie połączono - zapytaj sędziego głównego o adres IP"}</Text>
            <View style={styles.horizontalFlex}>
                <Text>Podaj adres ip serwera:</Text>
                <TextInput
                    style={localStyles.input}
                    value={ip}
                    onChangeText={setIp}
                />
                <Image source={require("../assets/database.png")} style={styles.bigIcon}/>
            </View>
            <TouchableOpacity style={localStyles.button} onPress={() => connectToSerive()}>
                <Text style={localStyles.textButton}>Połącz się</Text>
            </TouchableOpacity>
        </View>
    );
}