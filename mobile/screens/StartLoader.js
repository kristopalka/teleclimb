import {LeadStartPanel} from "./LeadStartPanel";
import {View, Text, TouchableOpacity} from "react-native";
import {BoulderingStartPanel} from "./BoulderingStartPanel";
import axios from "axios";
import {useEffect, useState} from "react";
import {ipAddressAndPort} from "../util/constants";

export function StartLoader({ip, navigateTo, discipline, navigation, participant, startId, hash}) {

    let data = participant;

    switch (discipline) {

        case "LEAD":
            return(<LeadStartPanel participant={data} navigator={navigateTo} startId={startId} hash={hash} ip={ip}/>);

        case "BOULDERING":
            return (<BoulderingStartPanel participant={data} navigator={navigateTo} startId={startId} hash={hash} ip={ip}/>);

        case "SPEED":
            return (
                <View>
                    <Text>Bieg - tym startem zarządza sędzia główny</Text>
                </View>
            );
    }

}