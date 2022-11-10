import {View, Text, SafeAreaView, FlatList, TouchableOpacity, StyleSheet} from "react-native";
import axios from "axios";
import {ipAddressAndPort} from "../util/constants";
import {useEffect, useState} from "react";

export function Competitions ({route, navigation}) {

    const styles = StyleSheet.create({
        container: {
            flex: 1,
            marginTop: 10,
        },
        item: {
            padding: 20,
            marginVertical: 8,
            marginHorizontal: 16,
        },
        title: {
            fontSize: 20,
        },
    });

    const [competitions, setCompetitions] = useState([]);
    const [rounds, setRounds] = useState([]);

    useEffect(() => {fetchAllCompetitions()}, []);

    function parseDiscipline(discipline) {
        switch (discipline) {
            case "BOULDERING":
                return "Bouldering";

            case "LEAD":
                return "Prowadzenie";

            case "SPEED":
                return "Bieg";
        }
        return discipline;
    }

    function genderParser(gender) {
        switch (gender) {
            case "FEMALE":
                return "Kobiety";
            case "MALE":
                return "Mężczyźni";
            case "UNISEX":
                return "Kategoria unierwersalna";
        }
        return gender;
    }

    async function fetchAllCompetitions() {
        await axios.get(ipAddressAndPort+"/competition/all")
            .then(response => setCompetitions(response.data))
            .catch(err => console.log(err))
    }

    function showRounds(competitionId) {
        navigation.navigate("Wybierz rundę");
        console.log("show rounds of competition of id="+competitionId);
    }

    const Item = ({item, onPress, backgroundColor, textColor}) => (
        <TouchableOpacity onPress={onPress} style={[styles.item, backgroundColor]}>
            <Text style={[styles.title, textColor]}>
                {item.id + ". " + item.name}
            </Text>
            <Text style={[styles.subtitle, textColor]}>
                {parseDiscipline(item.discipline) + " | " + genderParser(item.gender)}
            </Text>
        </TouchableOpacity>
    );

    const renderItem =({item})=> {

        const color = "white";
        const backgroundColor = "black";

        return(
          <Item
              item={item}
              onPress={() => navigation.navigate("Wybierz rundę", {competitionId: item.id, routeId: 1})}
              backgroundColor={{backgroundColor}}
              textColor={{color}}
          />
        );
    }

    return(
      <SafeAreaView style={styles.container}>
          <FlatList
            data={competitions}
            renderItem={renderItem}
            keyExtractor={(item) => item.id}
          />
      </SafeAreaView>
    );

}