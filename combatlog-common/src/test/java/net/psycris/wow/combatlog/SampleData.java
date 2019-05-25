package net.psycris.wow.combatlog;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public final class SampleData {
    private static final List<String> SAMPLE_ENTRIES = Arrays.asList(
            "1/13 21:26:32.024  COMBAT_LOG_VERSION,10,ADVANCED_LOG_ENABLED,1",
            "1/13 21:48:42.605  COMBATANT_INFO,Player-73-06DB52A5,0,893,6043,8080,1325,0,0,0,1317,1317,1317,74,0,1915,1915,1915,0,877,111,111,111,2559,254,(260309,260228,270581,193533,109215,260393,260404),(214027,203235,202589,202746),[212,375,0,459,375,0,14,375,0,13,375,0,212,375,2,459,375,2,14,375,2,13,375,2,483,375,4,22,375,4,15,375,4,13,375,4],[(157921,375,(),(1562,4786,5399),()),(158075,393,(),(4936,4929,4930),()),(163441,375,(),(5125,1562,4786,5400),()),(0,0,(),(),()),(160627,375,(),(4823,1492,4786),()),(160633,370,(0,0,5967),(4799,1808,1492,4786),(154127,120)),(160631,385,(),(4800,1808,1507,4786),()),(160628,375,(),(4799,1497,4783),()),(165487,385,(),(5126,1502,4786),()),(163448,370,(),(5125,42,1562,4786),()),(160646,370,(5943,0,0),(4799,1492,4786),()),(160647,370,(),(4799,1492,4786),()),(159614,375,(),(5009,1547,4786),()),(160652,390,(),(4800,1512,4783),()),(160643,370,(0,0,4897),(4799,1808,1492,4786),(154127,120)),(163879,370,(5957,0,0),(5125,4802,1562,4786),(154127,120)),(0,0,(),(),()),(0,0,(),(),())],[Player-73-0ABB9A35,1459,Player-73-06DB6D38,21562,Player-73-06DB52A5,270058,Player-73-06DB52A5,155228,Player-73-06DB52A5,251836,Player-73-06DB52A5,257415,Player-73-06DB52A5,280573]",
            "1/13 21:48:48.839  EMOTE,Creature-0-3134-1861-25453-137119-00003BE967,\"Taloc\",Player-73-0B8FFAC8,\"Séan\",|TInterface\\Icons\\INV_misc_boilingblood:20|tTaloc targets you with |cFFFF0000|Hspell:271224|h[Plasma Discharge]|h|r!",
            "1/13 21:53:19.842  ENCOUNTER_END,2144,\"Taloc\",15,13,1",
            "1/13 21:58:24.495  ENCOUNTER_START,2141,\"MOTHER\",15,15,1861",
            "1/13 22:28:35.020  ENVIRONMENTAL_DAMAGE,0000000000000000,nil,0x80000000,0x80000000,Player-73-09723151,\"Archsentinel-BleedingHollow\",0x512,0x0,Player-73-09723151,0000000000000000,159146,173880,7374,1611,3936,0,20000,20000,0,333.35,-44.80,1150,3.4498,378,Falling,14734,14734,0,1,0,0,0,nil,nil,nil",
            "1/13 22:28:38.025  PARTY_KILL,Player-73-0BC58182,\"Gentledraft-BleedingHollow\",0x40512,0x0,Creature-0-3134-1861-25453-136509-0000BBE968,\"Animated Rot\",0xa48,0x0",
            "1/13 21:27:33.058  RANGE_DAMAGE,Player-73-06DB52A5,\"Lanrefni-BleedingHollow\",0x514,0x0,Creature-0-3882-1861-10032-142242-00003BE35A,\"Warmother Ye'du\",0xa48,0x0,75,\"Auto Shot\",0x1,Creature-0-3882-1861-10032-142242-00003BE35A,0000000000000000,3302554,3366540,0,0,2700,1,0,0,0,83.58,-139.28,1150,3.5031,120,4914,3510,-1,1,0,0,0,1,nil,nil",
            "1/14 00:08:02.047  RANGE_MISSED,Player-73-06DB7A15,\"Sito-BleedingHollow\",0x514,0x0,Player-73-06DC913A,\"Rakmar-BleedingHollow\",0x1248,0x0,75,\"Auto Shot\",0x1,ABSORB,nil,34,4212",
            "1/14 00:08:02.165  SPELL_ABSORBED,Player-73-06DB6D38,\"Phibby-BleedingHollow\",0x514,0x0,Player-73-06DC913A,\"Rakmar-BleedingHollow\",0x1248,0x0,585,\"Smite\",0x2,Player-73-06DC913A,\"Rakmar-BleedingHollow\",0x1248,0x0,108366,\"Soul Leech\",0x20,92,4914",
            "1/14 00:08:01.551  SPELL_AURA_APPLIED,Player-73-06DB571B,\"Makasi-BleedingHollow\",0x514,0x0,Player-73-09723151,\"Archsentinel-BleedingHollow\",0x512,0x0,272260,\"Concentrated Mending\",0x2,BUFF",
            "1/14 00:08:01.596  SPELL_AURA_APPLIED_DOSE,Player-73-0BAFC000,\"Phátty-BleedingHollow\",0x514,0x0,Player-73-0BAFC000,\"Phátty-BleedingHollow\",0x514,0x0,278155,\"Lingering Power of Xalzaix\",0x1,BUFF,4",
            "1/15 22:21:06.883  SPELL_AURA_BROKEN,Player-73-06DBBB1C,\"Brehon-BleedingHollow\",0x514,0x0,Creature-0-3778-1861-20358-139487-00013E950B,\"Vision of Madness\",0xa48,0x0,197214,\"Sundering\",0x5,DEBUFF",
            "1/14 00:10:18.571  SPELL_AURA_BROKEN_SPELL,0000000000000000,nil,0x80000000,0x80000000,Player-73-06DF99C6,\"Sindarian-BleedingHollow\",0x514,0x8,111759,\"Levitate\",0x2,276863,\"Crashing Oblivion\",32,BUFF",
            "1/15 22:21:07.414  SPELL_AURA_REFRESH,Player-73-06DF99C6,\"Sindarian-BleedingHollow\",0x514,0x0,Player-73-09807134,\"Deamp-BleedingHollow\",0x514,0x0,139,\"Renew\",0x2,BUFF",
            "1/15 22:21:07.296  SPELL_AURA_REMOVED,Player-73-06DF99C6,\"Sindarian-BleedingHollow\",0x514,0x0,Player-73-0BAE69FE,\"Acyra-BleedingHollow\",0x40514,0x20,272260,\"Concentrated Mending\",0x2,BUFF",
            "1/15 22:21:07.518  SPELL_AURA_REMOVED_DOSE,Player-73-0BAE69FE,\"Acyra-BleedingHollow\",0x40514,0x20,Player-73-0BAE69FE,\"Acyra-BleedingHollow\",0x40514,0x20,271711,\"Overwhelming Power\",0x8,BUFF,6",
            "1/15 22:21:08.180  SPELL_CAST_FAILED,Player-73-0B8FFAC8,\"Séan-BleedingHollow\",0x511,0x0,0000000000000000,nil,0x80000000,0x80000000,193315,\"Sinister Strike\",0x1,\"Not yet recovered\"",
            "1/15 22:21:08.714  SPELL_CAST_START,Player-73-0B0F0FF7,\"Barebone-BleedingHollow\",0x512,0x0,0000000000000000,nil,0x80000000,0x80000000,30108,\"Unstable Affliction\",0x20",
            "1/15 22:21:08.714  SPELL_CAST_SUCCESS,Player-73-0B0F0FF7,\"Barebone-BleedingHollow\",0x512,0x0,Creature-0-3778-1861-20358-139487-00003E950B,\"Vision of Madness\",0xa48,0x0,30108,\"Unstable Affliction\",0x20,Player-73-0B0F0FF7,0000000000000000,170589,178660,3,7549,1142,7,30,50,10,237.48,-286.05,1155,4.2627,383",
            "1/15 22:32:14.823  SPELL_CREATE,Player-73-0B0F0FF7,\"Barebone-BleedingHollow\",0x512,0x0,GameObject-0-3778-1861-20358-303148-00003E97B0,\"Soulwell\",0x4228,0x0,29893,\"Create Soulwell\",0x20",
            "1/15 22:43:48.997  SPELL_DAMAGE,Player-73-0BAE69FE,\"Acyra-BleedingHollow\",0x40514,0x20,Vehicle-0-3778-1861-20358-134546-00003E99BE,\"Mythrax the Unraveler\",0x10a48,0x0,31935,\"Avenger's Shield\",0x2,Vehicle-0-3778-1861-20358-134546-00003E99BE,0000000000000000,38718441,38727000,0,0,2700,0,0,295105,0,278.08,-256.26,1155,3.1198,123,5149,5149,-1,2,0,0,0,nil,nil,nil",
            "1/15 22:51:48.806  SPELL_DISPEL,Player-73-0BAFC000,\"Phátty-BleedingHollow\",0x512,0x0,Player-73-06DB52A5,\"Lanrefni-BleedingHollow\",0x514,0x0,88423,\"Nature's Cure\",0x8,61391,\"Typhoon\",8,DEBUFF",
            "1/15 22:51:49.221  SPELL_ENERGIZE,Player-73-06DB52A5,\"Lanrefni-BleedingHollow\",0x514,0x0,Player-73-06DB52A5,\"Lanrefni-BleedingHollow\",0x514,0x0,77443,\"Steady Shot\",0x8,Player-73-06DB52A5,0000000000000000,163400,163400,8164,1205,2559,2,92,100,0,218.44,-231.84,1155,5.9056,377,10.0000,0.0000,2,100",
            "1/15 21:30:11.771  SPELL_EXTRA_ATTACKS,Creature-0-3137-1642-14036-126618-00023E7CF8,\"Bloodraged Pterrordax\",0xa48,0x0,Creature-0-3137-1642-14036-126618-00023E7CF8,\"Bloodraged Pterrordax\",0xa48,0x0,3391,\"Thrash\",0x1,2",
            "1/15 21:30:50.754  SPELL_HEAL,0000000000000000,nil,0x518,0x0,Player-57-0B361C4A,\"Soulsbane-Illidan\",0x518,0x0,275762,\"Azerite\",0x40,Player-57-0B361C4A,0000000000000000,165770,165770,3,7370,1108,0,100000,100000,0,793.24,1400.69,863,1.5672,378,5230,5230,5230,0,nil",
            "1/15 22:15:16.858  SPELL_HEAL_ABSORBED,Player-73-06DB6D38,\"Phibby-BleedingHollow\",0x514,0x0,Player-73-099C737E,\"Manmower-BleedingHollow\",0x512,0x0,219521,\"Shadow Covenant\",0x20,Player-73-06DB6D38,\"Phibby-BleedingHollow\",0x514,0x0,81751,\"Atonement\",0x2,319,319",
            "1/20 21:34:55.069  SPELL_INSTAKILL,Creature-0-3882-1861-32355-138530-00004521C0,\"Volatile Droplet\",0xa48,0x0,Creature-0-3882-1861-32355-138530-00004521C0,\"Volatile Droplet\",0xa48,0x0,272586,\"Combustible Fuel\",0x4",
            "1/20 21:47:14.778  SPELL_INTERRUPT,Player-73-09723151,\"Archsentinel-BleedingHollow\",0x514,0x0,Creature-0-3882-1861-32355-136499-0002451D0A,\"Nazmani Ascendant\",0xa48,0x0,96231,\"Rebuke\",0x1,276540,\"Blood Shield\",32",
            "1/20 21:47:18.452  SPELL_MISSED,Player-73-0BAE69FE,\"Acyra-BleedingHollow\",0x60514,0x0,Creature-0-3882-1861-32355-136496-0002451D0A,\"Nazmani Chosen\",0xa48,0x0,204242,\"Consecration\",0x2,IMMUNE,nil",
            "1/20 21:47:19.452  SPELL_PERIODIC_ENERGIZE,Player-73-06DB7A15,\"Sito-BleedingHollow\",0x514,0x0,Player-73-06DB7A15,\"Sito-BleedingHollow\",0x514,0x0,246851,\"Barbed Shot\",0x1,Player-73-06DB7A15,0000000000000000,189200,189200,9178,1322,2734,2,64,120,0,38.96,-329.11,1150,6.0944,383,5.0000,0.0000,2,120",
            "1/20 21:47:18.750  SPELL_PERIODIC_DAMAGE,Player-73-0B8FFAC8,\"Séan-BleedingHollow\",0x511,0x0,Creature-0-3882-1861-32355-136499-0002C51D0A,\"Nazmani Ascendant\",0xa48,0x0,280286,\"Dagger in the Back\",0x1,Creature-0-3882-1861-32355-136499-0002C51D0A,0000000000000000,236036,1036300,0,0,2700,1,0,0,0,46.18,-333.78,1150,2.1850,120,1808,860,-1,1,0,0,0,1,nil,nil",
            "1/20 21:47:18.783  SPELL_PERIODIC_HEAL,Player-73-06DF99C6,\"Sindarian-BleedingHollow\",0x514,0x8,Player-73-09723151,\"Archsentinel-BleedingHollow\",0x514,0x0,139,\"Renew\",0x2,Player-73-09723151,0000000000000000,176180,176180,8808,1611,3950,0,20000,20000,0,47.02,-331.84,1150,2.9315,381,2541,2541,2541,0,nil",
            "1/20 21:47:32.812  SPELL_PERIODIC_MISSED,Player-73-0BC58182,\"Gentledraft-BleedingHollow\",0x40514,0x0,Player-73-0BC58182,\"Gentledraft-BleedingHollow\",0x40514,0x0,124255,\"Stagger\",0x1,ABSORB,nil,963,963",
            "1/20 22:04:21.304  SPELL_RESURRECT,Player-73-06DC236B,\"Haman-BleedingHollow\",0x514,0x0,Player-73-0B9CE2D9,\"Adinatus-BleedingHollow\",0x512,0x0,61999,\"Raise Ally\",0x20",
            "1/16 00:52:53.587  SPELL_STOLEN,Player-1427-080574AE,\"Julizzio-Ragnaros\",0x514,0x0,Creature-0-3780-2111-11340-149311-00003EB890,\"Shandris Feathermoon\",0xa48,0x0,30449,\"Spellsteal\",0x40,290605,\"Ferocious Howl\",1,BUFF",
            "1/16 00:53:01.453  SPELL_SUMMON,Player-1427-080574AE,\"Julizzio-Ragnaros\",0x514,0x0,Pet-0-3780-2111-11340-78116-02025355F1,\"Elemental de agua\",0x1228,0x0,31687,\"Summon Water Elemental\",0x10",
            "1/16 00:53:01.571  SWING_DAMAGE,Creature-0-3780-2111-11340-144877-00003EB724,\"Forsaken Lancer\",0x2112,0x0,Creature-0-3780-2111-11340-149312-00013EB890,\"Ferocious Swiftclaw\",0xa48,0x0,Creature-0-3780-2111-11340-144877-00003EB724,Player-11-09466693,260166,260166,0,0,2700,0,295105,295105,0,7387.50,-97.65,1332,6.0284,336,6819,9741,-1,1,0,0,0,nil,nil,nil",
            "1/16 00:53:01.723  SWING_DAMAGE_LANDED,Creature-0-3780-2111-11340-144877-00003EB724,\"Forsaken Lancer\",0x2112,0x0,Creature-0-3780-2111-11340-149312-00013EB890,\"Ferocious Swiftclaw\",0xa48,0x0,Creature-0-3780-2111-11340-149312-00013EB890,0000000000000000,62264,307430,0,0,2700,1,0,0,0,7390.11,-98.33,1332,0.9299,121,6819,9741,-1,1,0,0,0,nil,nil,nil",
            "1/16 00:53:03.671  SWING_MISSED,Player-154-08ECF106,\"Trumptrain-Shadowmoon\",0x514,0x0,Creature-0-3780-2111-11340-149311-00003EB890,\"Shandris Feathermoon\",0xa48,0x0,MISS,nil",
            "1/16 00:53:14.065  UNIT_DIED,0000000000000000,nil,0x80000000,0x80000000,Creature-0-3780-2111-11340-146770-00003EB8AE,\"Druid of the Claw\",0xa48,0x0",
            "1/20 22:15:08.877  WORLD_MARKER_PLACED,1861,5,670.17,-245.15",
            "1/20 22:49:45.606  WORLD_MARKER_REMOVED,1"
    );

    public static List<String> getSampleEntries() {
        return SAMPLE_ENTRIES;
    }

    private SampleData() {
    }
}
