package com.tecpro.mcapp;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jjaimez on 14/1/16.
 */
public class Img {

    ArrayList<Integer> values;


    public Img() {
        int[] ls = {R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5,
                R.drawable.i6, R.drawable.i7, R.drawable.i8, R.drawable.i9, R.drawable.i10, R.drawable.i11
                , R.drawable.i12
                , R.drawable.i13
                , R.drawable.i14
                , R.drawable.i15
                , R.drawable.i16
                , R.drawable.i17
                , R.drawable.i18
                , R.drawable.i19
                , R.drawable.i20
                , R.drawable.i21
                , R.drawable.i22
                , R.drawable.i23
                , R.drawable.i24
                , R.drawable.i25
                , R.drawable.i26
                , R.drawable.i27
                , R.drawable.i28
                , R.drawable.i29
                , R.drawable.i30
                , R.drawable.i31
                , R.drawable.i32
                , R.drawable.i33
                , R.drawable.i34
                , R.drawable.i35
                , R.drawable.i36
                , R.drawable.i37
                , R.drawable.i38
                , R.drawable.i39
                , R.drawable.i40
                , R.drawable.i41
                , R.drawable.i42
                , R.drawable.i43
                , R.drawable.i44
                , R.drawable.i45
                , R.drawable.i46
                , R.drawable.i47
                , R.drawable.i48
                , R.drawable.i49
                , R.drawable.i50
                , R.drawable.i51
                , R.drawable.i52
                , R.drawable.i53
                , R.drawable.i54
                , R.drawable.i55
                , R.drawable.i56
                , R.drawable.i57
                , R.drawable.i58
                , R.drawable.i59
                , R.drawable.i60
                , R.drawable.i61
                , R.drawable.i62
                , R.drawable.i63
                , R.drawable.i64
                , R.drawable.i65
                , R.drawable.i66
                , R.drawable.i67
                , R.drawable.i68
                , R.drawable.i69
                , R.drawable.i70
                , R.drawable.i71
                , R.drawable.i72
                , R.drawable.i73
                , R.drawable.i74
                , R.drawable.i75
                , R.drawable.i76
                , R.drawable.i77
                , R.drawable.i78
                , R.drawable.i79
                , R.drawable.i80
                , R.drawable.i81
                , R.drawable.i82
                , R.drawable.i83
                , R.drawable.i84
                , R.drawable.i85
                , R.drawable.i86
                , R.drawable.i87
                , R.drawable.i88
                , R.drawable.i89
                , R.drawable.i90,R.drawable.i91,R.drawable.i92,R.drawable.i93,R.drawable.i94,R.drawable.i95,R.drawable.i96,R.drawable.i97,R.drawable.i98,R.drawable.i99,R.drawable.i100,R.drawable.i101,R.drawable.i102,R.drawable.i103,R.drawable.i104,R.drawable.i105,R.drawable.i106,R.drawable.i107,R.drawable.i108,R.drawable.i109,R.drawable.i110,R.drawable.i111,R.drawable.i112,R.drawable.i113,R.drawable.i114,R.drawable.i115,R.drawable.i116,R.drawable.i117,R.drawable.i118,R.drawable.i119,R.drawable.i120,R.drawable.i121,R.drawable.i122,R.drawable.i123,R.drawable.i124,R.drawable.i125,R.drawable.i126,R.drawable.i127,R.drawable.i128,R.drawable.i129,R.drawable.i130,R.drawable.i131,R.drawable.i132,R.drawable.i133,R.drawable.i134,R.drawable.i135,R.drawable.i136,R.drawable.i137,R.drawable.i138,R.drawable.i139,R.drawable.i140,R.drawable.i141,R.drawable.i142,R.drawable.i143,R.drawable.i144,R.drawable.i145,R.drawable.i146,R.drawable.i147,R.drawable.i148,R.drawable.i149,R.drawable.i150,R.drawable.i151,R.drawable.i152,R.drawable.i153,R.drawable.i154,R.drawable.i155,R.drawable.i156,R.drawable.i157,R.drawable.i158,R.drawable.i159,R.drawable.i160,R.drawable.i161,R.drawable.i162,R.drawable.i163,R.drawable.i164,R.drawable.i165,R.drawable.i166,R.drawable.i167,R.drawable.i168,R.drawable.i169,R.drawable.i170,R.drawable.i171,R.drawable.i172,R.drawable.i173,R.drawable.i174,R.drawable.i175,R.drawable.i176,R.drawable.i177,R.drawable.i178,R.drawable.i179,R.drawable.i180,R.drawable.i181,R.drawable.i182,R.drawable.i183,R.drawable.i184,R.drawable.i185,R.drawable.i186,R.drawable.i187,R.drawable.i188,R.drawable.i189,R.drawable.i190,R.drawable.i191,R.drawable.i192,R.drawable.i193,R.drawable.i194,R.drawable.i195,R.drawable.i196,R.drawable.i197,R.drawable.i198,R.drawable.i199,R.drawable.i200,R.drawable.i201,R.drawable.i202,R.drawable.i203,R.drawable.i204,R.drawable.i205,R.drawable.i206,R.drawable.i207,R.drawable.i208,R.drawable.i209,R.drawable.i210,R.drawable.i211,R.drawable.i212,R.drawable.i213,R.drawable.i214,R.drawable.i215,R.drawable.i216,R.drawable.i217,R.drawable.i218,R.drawable.i219,R.drawable.i220,R.drawable.i221,R.drawable.i222,R.drawable.i223,R.drawable.i224,R.drawable.i225,R.drawable.i226,R.drawable.i227,R.drawable.i228,R.drawable.i229,R.drawable.i230,R.drawable.i231,R.drawable.i232,R.drawable.i233,R.drawable.i234,R.drawable.i235,R.drawable.i236,R.drawable.i237,R.drawable.i238,R.drawable.i239,R.drawable.i240,R.drawable.i241,R.drawable.i242,R.drawable.i243,R.drawable.i244,R.drawable.i245,R.drawable.i246,R.drawable.i247,R.drawable.i248,R.drawable.i249,R.drawable.i250,R.drawable.i251,R.drawable.i252,R.drawable.i253,R.drawable.i254,R.drawable.i255,R.drawable.i256,R.drawable.i257,R.drawable.i258,R.drawable.i259,R.drawable.i260,R.drawable.i261,R.drawable.i262,R.drawable.i263,R.drawable.i264,R.drawable.i265,R.drawable.i266,R.drawable.i267,R.drawable.i268,R.drawable.i269,R.drawable.i270,R.drawable.i271,R.drawable.i272,R.drawable.i273,R.drawable.i274,R.drawable.i275,R.drawable.i276,R.drawable.i277,R.drawable.i278,R.drawable.i279,R.drawable.i280,R.drawable.i281,R.drawable.i282,R.drawable.i283,R.drawable.i284,R.drawable.i285,R.drawable.i286,R.drawable.i287,R.drawable.i288,R.drawable.i289,R.drawable.i290,R.drawable.i291,R.drawable.i292,R.drawable.i293,R.drawable.i294,R.drawable.i295,R.drawable.i296,R.drawable.i297,R.drawable.i298,R.drawable.i299,R.drawable.i300,R.drawable.i301,R.drawable.i302,R.drawable.i303,R.drawable.i304,R.drawable.i305,R.drawable.i306,R.drawable.i307,R.drawable.i308,R.drawable.i309,R.drawable.i310,R.drawable.i311,R.drawable.i312,R.drawable.i313,R.drawable.i314,R.drawable.i315,R.drawable.i316,R.drawable.i317,R.drawable.i318,R.drawable.i319,R.drawable.i320,R.drawable.i321,R.drawable.i322,R.drawable.i323,R.drawable.i324,R.drawable.i325,R.drawable.i326,R.drawable.i327,R.drawable.i328,R.drawable.i329,R.drawable.i330,R.drawable.i331,R.drawable.i332,R.drawable.i333,R.drawable.i334,R.drawable.i335,R.drawable.i336,R.drawable.i337,R.drawable.i338,R.drawable.i339,R.drawable.i340,R.drawable.i341,R.drawable.i342,R.drawable.i343,R.drawable.i344,R.drawable.i345,R.drawable.i346,R.drawable.i347,R.drawable.i348,R.drawable.i349,R.drawable.i350,R.drawable.i351,R.drawable.i352,R.drawable.i353,R.drawable.i354,R.drawable.i355,R.drawable.i356,R.drawable.i357,R.drawable.i358,R.drawable.i359,R.drawable.i360,R.drawable.i361,R.drawable.i362,R.drawable.i363,R.drawable.i364,R.drawable.i365,R.drawable.i367,R.drawable.i368,R.drawable.i369,R.drawable.i370,R.drawable.i371,R.drawable.i372,R.drawable.i373,R.drawable.i374,R.drawable.i375,R.drawable.i376,R.drawable.i377,R.drawable.i378,R.drawable.i379,R.drawable.i380,R.drawable.i381,R.drawable.i382,R.drawable.i383,R.drawable.i384,R.drawable.i385,R.drawable.i386,R.drawable.i387,R.drawable.i388,R.drawable.i389,R.drawable.i390,R.drawable.i391,R.drawable.i392,R.drawable.i393,R.drawable.i394,R.drawable.i395,R.drawable.i396,R.drawable.i397,R.drawable.i398,R.drawable.i399,R.drawable.i400,R.drawable.i401,R.drawable.i402,R.drawable.i403,R.drawable.i404,R.drawable.i405,R.drawable.i406,R.drawable.i366};
        values = new ArrayList<>();
        for (int i = 0; i <ls.length ; i++) {
            values.add(ls[i]);
        }
    }

    public int nextImage(){
        SecureRandom secureRandom = new SecureRandom();
        int ret;
        ret = secureRandom.nextInt(values.size());
        int r = values.get(ret);
        values.remove(ret);
        return r;
    }

}
