import {Button, Image, Text, View, TouchableOpacity} from "react-native";
import {styles} from "../styles";
import {useEffect, useState} from "react";
import axios from "axios";
import {ipAddressAndPort} from "../util/constants";
import {Connector} from "./Connector";

export function Home ({navigation}) {

    const [ip, setIp] = useState("");

    function saveIp(ip) {
        setIp(ip);
    }

    function navigateToRefereeRegister() {
        if(ip.length > 0) navigation.navigate("Zarejestruj się", {ip: ip});
    }

    function navigateToChoosingCompetition() {
        if(ip.length > 0) navigation.navigate("Wybierz zawody");
    }

    function navigateToHistory() {
        if(ip.length > 0) navigation.navigate("Historia");
    }

    return (
        <View style={styles.container}>
            <Image source={require("../assets/climbingLogo1000.png")} style={styles.logo}/>
            <View style={styles.upContainer}>
                <Text style={styles.title}>TeleClimb</Text>
                <Text style={styles.subtitle}>Aplikacja dla sędziów problemów na zawodach wspinaczkowych</Text>
            </View>
            <Connector navigation={navigation} saveIp={saveIp}/>
            <View style={styles.menu}>
                <TouchableOpacity style={styles.menuItem} onPress={() => navigateToChoosingCompetition()}>
                    <Image source={require("../assets/competition.png")} style={styles.icon}/>
                    <Text style={styles.menuText}>Konkurencje</Text>
                </TouchableOpacity>
                <TouchableOpacity style={styles.menuItem} onPress={() => navigateToRefereeRegister()}>
                    <Image source={require("../assets/running.png")} style={styles.icon}/>
                    <Text style={styles.menuText}>Sędziuj</Text>
                </TouchableOpacity>
                <TouchableOpacity style={styles.menuItem} onPress={() => navigateToHistory()}>
                    <Image source={require("../assets/history.png")} style={styles.icon}/>
                    <Text style={styles.menuText}>Historia</Text>
                </TouchableOpacity>
            </View>
        </View>
    );
}