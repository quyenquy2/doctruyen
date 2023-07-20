package com.example.appdoctruyen_v2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.appdoctruyen_v2.MainNoiDungTruyen;
import com.example.appdoctruyen_v2.model.DanhGia;
import com.example.appdoctruyen_v2.model.TaiKhoan;
import com.example.appdoctruyen_v2.model.Truyen;
import com.example.appdoctruyen_v2.model.yeuthich;

//select distinct * from dsyeuthich
public class databasedoctruyen extends SQLiteOpenHelper{

    public  String a;
    private static String DATABASE_NAME = "doctruyen";
    private static String TABLE_TAIKHOAN = "taikhoan";
    private static String ID_TAI_KHOAN = "idtaikhoan";
    private static String TEN_TAI_KHOAN = "tentaikhoan";
    private static String MAT_KHAU = "matkhau";
    private static String PHAN_QUYEN = "phanquyen";
    private static String EMAIL = "email";
    private static int VERSION = 2;

    private static String TABLE_TRUYEN = "truyen";
    private static String ID_TRUYEN = "idtruyen";
    private static String TEN_TRUYEN = "tieude";
    private static String NOI_DUNG = "noidung";
    private static String IMAGE = "anh";
    private static String SL_YEUTHICH ="soluongyt";

    private static String TABLE_DANH_GIA = "danhgia";
    private static String ID_DANH_GIA = "iddanhgia";
    private static String NOI_DUNG_DANH_GIA = "noidungdanhgia";

    private static String TABLE_DS_YEU_THICH = "dsyeuthich";
    private static String TEN_TAI_KHOAN_YT = "tentaikhoan";
    private static String TIEU_DE_YT = "tieude";



    private Context context;

    private String SQLQueryYT = "CREATE TABLE dsyeuthich (\n" +
            "\t tentaikhoan  text ,\n" +
            "\t tieude text ,\n" +
            "\t FOREIGN KEY (tieude) REFERENCES truyen(tieude),\n" +
            "\tFOREIGN KEY (tentaikhoan) REFERENCES taikhoan(tentaikhoan));";

    private String SQLQuery = "CREATE TABLE "+ TABLE_TAIKHOAN +" ( "+ID_TAI_KHOAN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TEN_TAI_KHOAN+" TEXT UNIQUE, "
            +MAT_KHAU+" TEXT, "
            +EMAIL+" TEXT, "
            + PHAN_QUYEN+" INTEGER) ";

    private String SQLQuery1 = "CREATE TABLE "+ TABLE_TRUYEN +" ( "+ID_TRUYEN+" integer primary key AUTOINCREMENT, "
            +TEN_TRUYEN+" TEXT UNIQUE, "
            +NOI_DUNG+" TEXT, "
            +IMAGE+" TEXT, "+SL_YEUTHICH+" INTEGER )";

    private String SQLQuery13 = "CREATE TABLE "+ TABLE_DANH_GIA +" ( "+ID_DANH_GIA+" integer primary key AUTOINCREMENT, "
            +NOI_DUNG_DANH_GIA+" TEXT, "
            +TEN_TRUYEN+" TEXT, "
            +TEN_TAI_KHOAN+" TEXT )";





    private String SQLQuery4 = "INSERT INTO truyen VALUES (null,'Doraemon','Mùa đông đã đến rồi trời lạnh buốt, Doraemon không có gì để ăn cả. Doraemon mặc áo vào rồi ra ngoài kiếm thức ăn. Nó đi mãi đi mãi cuối cùng cũng tìm được hai củ cải trắng. Doraemon reo lên:\n" +
            "\n" +
            "– Ôi, ở đây có hai củ cải trắng liền, mình thật là may mắn!\n" +
            "\n" +
            "Doraemon đói bụng, muốn ăn lắm rồi. Nhưng Doraemon lại nghĩ:\n" +
            "\n" +
            "– Ừm… trời lạnh thế này, chắc Nobita cũng không có cái gì để ăn đâu. Mình phải mang cho Nobita một củ mới được.\n" +
            "\n" +
            "Thế là Doraemon đi sang nhà bạn Nobita nhưng Nobita không có nhà nên Doraemon đặt củ cải lên bàn rồi đi về.\n" +
            "\n" +
            "Tình cờ, Nobita con đi chơi cũng tìm được một củ cải trắng nhưng nó chỉ ăn trước một nửa.\n" +
            "\n" +
            "Về đến nhà, lại thấy có một củ cải trắng ở trên bàn Nobita thèm ăn lắm, nhưng lại nghĩ:\n" +
            "\n" +
            "– Ôi trời lạnh thế này chắc Shizuka không có cái gì để ăn rồi, mình phải mang cho Shizuka mới được.\n" +
            "\n" +
            "Nobita con đến nhà Shizuka nhưng Shizuka lại đi vắng, Nobita bèn đặt củ cải ở trên bàn rồi về.\n" +
            "\n" +
            "Khi Shizuka về nhà, thấy củ cải ở trên bàn, Shizuka ngạc nhiên lắm.\n" +
            "\n" +
            "– Ồ, củ cải trắng ở đâu mà ngon vậy nhỉ. Xuỵt… thích quá. Nhưng chắc trời lạnh thế này, Doraemon cũng không có gì ăn đâu. Mình phải mang sang cho Doraemon mới được.\n" +
            "\n" +
            "Khi Shizuka đến thì Doraemon đang ngủ rất say. Khi tỉnh dậy Doraemon lại thấy trên bàn mình xuất hiện một củ cải trắng.Doraemon vui lắm nó chạy đi gọi các bạn:\n" +
            "\n" +
            "– Bạn Shizuka ơi, bạn Nobita ơi hãy đến nhà tôi, chúng ta cùng ăn củ cải trắng thơm ngon này.\n" +
            "\n" +
            "Thế là cuối cùng, củ cải trắng ấy được chia sẻ cho cả ba người bạn tốt bụng của chúng ta. Các bạn thấy đấy tấm lòng thơm thảo, sẵn sàng sẻ chia của các bạn ấy thật là đáng học tập phải không nào?\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Khi cho đi bạn sẽ nhận lại được nhiều hơn những thứ mình có.','https://vn-live-01.slatic.net/p/066332c4759d868e4abf0cc20fb6924d.jpg',0)";
    private String SQLQuery5 = "INSERT INTO truyen VALUES (null,'Conan','Mùa đông đã đến rồi trời lạnh buốt, Conan không có gì để ăn cả. Conan mặc áo vào rồi ra ngoài kiếm thức ăn. Nó đi mãi đi mãi cuối cùng cũng tìm được hai củ cải trắng. Conan reo lên:\n" +
            "\n" +
            "– Ôi, ở đây có hai củ cải trắng liền, mình thật là may mắn!\n" +
            "\n" +
            "Conan đói bụng, muốn ăn lắm rồi. Nhưng Conan lại nghĩ:\n" +
            "\n" +
            "– Ừm… trời lạnh thế này, chắc Ran cũng không có cái gì để ăn đâu. Mình phải mang cho Ran con một củ mới được.\n" +
            "\n" +
            "Thế là Conan con đi sang nhà bạn Ran nhưng Ran không có nhà nên Conan đặt củ cải lên bàn rồi đi về.\n" +
            "\n" +
            "Tình cờ, Ran đi chơi cũng tìm được một củ cải trắng nhưng nó chỉ ăn trước một nửa.\n" +
            "\n" +
            "Về đến nhà, lại thấy có một củ cải trắng ở trên bàn Ran thèm ăn lắm, nhưng lại nghĩ:\n" +
            "\n" +
            "– Ôi trời lạnh thế này chắc Haibara không có cái gì để ăn rồi, mình phải mang cho Haibara mới được.\n" +
            "\n" +
            "Ran đến nhà Haibara nhưng Haibara lại đi vắng, Ran bèn đặt củ cải ở trên bàn rồi về.\n" +
            "\n" +
            "Khi Haibara về nhà, thấy củ cải ở trên bàn, Haibara ngạc nhiên lắm.\n" +
            "\n" +
            "– Ồ, củ cải trắng ở đâu mà ngon vậy nhỉ. Xuỵt… thích quá. Nhưng chắc trời lạnh thế này, Conan con cũng không có gì ăn đâu. Mình phải mang sang cho Conan mới được.\n" +
            "\n" +
            "Khi Haibara đến thì Conan con đang ngủ rất say. Khi tỉnh dậy Conan lại thấy trên bàn mình xuất hiện một củ cải trắng.Conan vui lắm nó chạy đi gọi các bạn:\n" +
            "\n" +
            "– Bạn Haibara ơi, bạn Ran ơi hãy đến nhà tôi, chúng ta cùng ăn củ cải trắng thơm ngon này.\n" +
            "\n" +
            "Thế là cuối cùng, củ cải trắng ấy được chia sẻ cho cả ba người bạn tốt bụng của chúng ta. Các bạn thấy đấy tấm lòng thơm thảo, sẵn sàng sẻ chia của các bạn ấy thật là đáng học tập phải không nào?\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Khi cho đi bạn sẽ nhận lại được nhiều hơn những thứ mình có.','https://thuthuatnhanh.com/wp-content/uploads/2022/03/Anh-Conan-ngau-1.jpg',0)";
    private String SQLQuery6 = "INSERT INTO truyen VALUES (null,'One Piece','Luffy đen và Luffy trắng cùng sống trong một khu rừng. Hàng ngày, cả hai thường đến uống nước và tìm cái ăn ở trong khu rừng quen thuộc.\n" +
            "\n" +
            "Một hôm, Luffy trắng đi tìm cái ăn và uống nước suối như mọi khi. Luffy đang mải mê ngặm cỏ, bất chợt một con Sói ở đâu nhảy xổ ra. Sói quát hỏi:\n" +
            "\n" +
            "- Luffy kia! Mi đi đâu?\n" +
            "\n" +
            "Luffy trắng sợ rúm cả người, lắp bắp:\n" +
            "\n" +
            "– Dạ, dạ, tôi đi tìm… tìm cỏ non và…và uống nước suối ạ!\n" +
            "\n" +
            "Sói lại quát hỏi:\n" +
            "\n" +
            "– Mi có gì ở chân?\n" +
            "\n" +
            "– Dạ, dạ, chân của tôi có móng ạ…ạ!\n" +
            "\n" +
            "– Trên đầu mi có gì?\n" +
            "\n" +
            "– Dạ, dạ, trên đầu tôi có đôi sừng mới nhú…\n" +
            "\n" +
            "Sói càng quát to hơn:\n" +
            "\n" +
            "– Trái tim mi thế nào?\n" +
            "\n" +
            "– Ôi, ôi, trái…trái tim tôi đang run sợ…sợ…\n" +
            "\n" +
            "– Hahaha…\n" +
            "\n" +
            "Sói cười vang rồi ăn thịt chú Luffy trắng tội nghiệp\n" +
            "\n" +
            "Luffy đen cũng đi tới khu rừng để ăn cỏ non và uống nước suối. Đang tha thẩn ngặm cỏ, chợt Sói xuất hiện, nó quát hỏi:\n" +
            "\n" +
            "– Luffy kia, mi đi đâu?\n" +
            "\n" +
            "Luffy đen nhìn con Sói từ đầu tới chân rồi ngước cổ trả lời:\n" +
            "\n" +
            "– Ta đi tìm kẻ nào thích gây sự đây!\n" +
            "\n" +
            "Sói bị bất ngờ, nó hỏi tiếp:\n" +
            "\n" +
            "– Thế dưới chân mi có gì?\n" +
            "\n" +
            "– Chân thép của ta có móng bằng đồng.\n" +
            "\n" +
            "– Thế…thế…trên đầu mi có gì?\n" +
            "\n" +
            "– Trên đầu của ta có đôi sừng bằng kim cương!\n" +
            "\n" +
            "Sói sợ lắm rồi, nhưng vẫn cố hỏi:\n" +
            "\n" +
            "– Mi…mi…trái tim mi thế nào?\n" +
            "\n" +
            "Luffy đen dõng dạc trả lời:\n" +
            "\n" +
            "– Trái tim thép của ta bảo ta rằng: hãy cắm đôi sừng kim cương vào đầu Sói. Nào, Sói hãy lại đây.\n" +
            "\n" +
            "Ôi trời, sợ quá, con Sói ba chân bốn cẳng chạy biến vào rừng, từ đó không ai trông thấy nó lởn vởn ở khu rừng đó nữa.\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Qua câu chuyện ngụ ngôn trên, bạn có thể truyền tải nhiều thông điệp khác nhau cho bé hiểu. Chẳng hạn như biết cách ứng xử trước các tình huống khó, nguy hiểm, lạc quan và bản lĩnh để xử lý vấn đề.','https://i.pinimg.com/originals/3a/3c/ab/3a3cabd94347cf64dade52882308c780.jpg',0)";
    private String SQLQuery7 = "INSERT INTO truyen VALUES (null,'Dragon ball','Một chú bé chăn cừu cho chủ thả cừu gần một khu rừng rậm cách làng không xa lắm. Chăn cừu được ít lâu, chú cảm thấy công việc chăn cừu thực là nhàm chán. Tất cả mọi việc chú có thể làm để giải khuây là nói chuyện với con chó hoặc thổi chiếc sáo chăn cừu của mình.\n" +
            "\n" +
            "Một hôm, trong lúc đang ngắm nhìn đàn cừu và cánh rừng yên tĩnh chú bé chợt nhớ tới lời chủ của chú từng dặn rằng khi sói tấn công cừu thì phải kêu cứu để dân làng nghe thấy và đánh đuổi nó đi.\n" +
            "\n" +
            "Thế là chú nghĩ ra một trò chơi cho đỡ buồn. Mặc dù chẳng thấy con sói nào, chú cứ chạy về làng và la to:\n" +
            "\n" +
            "– Sói ! Sói !\n" +
            "\n" +
            "Đúng như chú nghĩ, dân làng nghe thấy tiếng kêu bỏ cả việc làm và tức tốc chạy ra cánh đồng. Nhưng khi đến nơi, họ chẳng thấy sói đâu, chỉ thấy chú bé ôm bụng cười ngặt nghẽo vì đã lừa được họ.\n" +
            "\n" +
            "Ít ngày sau chú bé chăn cừu lần nữa lại la lên:\n" +
            "\n" +
            "– “Sói ! Sói !”.\n" +
            "\n" +
            "Và một lần nữa dân làng lại chạy ra giúp chú. Nhưng họ lại chẳng thấy con sói nào, chỉ thấy chú bé chăn cừu nghịch ngợm ôm bụng cười khoái chí.\n" +
            "\n" +
            "Thế rồi vào một buổi chiều nọ, khi mặt trời lặn xuống sau cánh rừng và bóng tối bắt đầu phủ đầy lên cánh đồng, một con sói thực sự xuất hiện. Nó nấp sau bụi cây rình bắt những con cừu béo non. Bỗng sói phóng vút ra chộp lấy một chú cừu tội nghiệp. Thấy sói cả đàn cừu sợ hãi chạy toán loạn, chú bé chăn cừu cũng hoảng loạn vô cùng.\n" +
            "\n" +
            "Quá hoảng sợ, chú bé chăn cừu vội chạy về làng và la to:\n" +
            "\n" +
            "– “Sói ! Sói !”.\n" +
            "\n" +
            "Nhưng mặc dù dân làng có nghe tiếng chú kêu, không một ai chạy ra để giúp chú như những lần trước cả. Vì mọi người nghĩ chú lại bày trò nói dối như trước.\n" +
            "\n" +
            "Thế là sói thỏa sức bắt mồi, giết chết rất nhiều cừu của chú bé. Sau khi đã chén no nê, nó biến mất vào rừng rậm. Chú bé buồn bã ngồi giữa đồng cỏ, lòng đầy hối hận về hành động nói dối của mình và hậu quả của trò đùa dại dột gây ra.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Nói dối là một tật xấu. Người hay nói dối ngay cả khi nói thật cũng không ai tin.','https://static.gamehub.vn/img/files/2020/07/30/gamehubvn-game-nguoi-dan-ong-32-tuoi-bi-bat-vi-ban-bat-lua-an-theo-game-2.jpg',0)";
    private String SQLQuery8 = "INSERT INTO truyen VALUES (null,'GIÀ THIÊN','Sinh mệnh là kỳ tích vĩ đại nhất của thế gian.\n" +
            "\n" +
            "Bốn phương trên dưới viết chữ Vũ. Vũ tuy có thực, nhưng vô định không thể cầu được. Từ cổ chí kim viết chữ Trụ. Trụ tuy có tăng trưởng, nhưng không biết ngọn nguồn của nó.\n" +
            "\n" +
            "Vũ trụ mênh mông, tinh không vô ngần, rất nhiều nhà khoa học đã suy đoán, Địa Cầu là vùng đất duy nhất có sự sống.\n" +
            "\n" +
            "Nhân loại kỳ thực rất cô độc. Bên trong vũ trụ mênh mông già cỗi, mặc dù có hàng tỷ hành tinh, thế nhưng lại rất khó có thể tìm được hành tinh có sự sống thứ hai.\n" +
            "\n" +
            "Tuy nhiên nhân loại chưa từng từ bỏ việc tìm kiếm, từ thế kỷ trước tới nay đã phóng rất nhiều tàu thăm dò vũ trụ.\n" +
            "\n" +
            "Người lữ hành số 2 là một con tàu thăm dò vũ trụ không người lái, Năm 1977, tại Trung tâm hàng không vũ trụ Kennedy - Mĩ phóng lên không trung.\n" +
            "\n" +
            "Mặt trên của nó mang theo một đĩa nhạc mạ vàng có chủ đề là \"Gửi lời hỏi thăm tới Vũ Trụ\", bên trong còn có một số thể loại âm nhạc đang thịnh hành cùng những lời hỏi thăm được nói bằng 55 thứ ngôn ngữ trên địa cầu, hi vọng sẽ có một ngày, được một nền văn minh khác thu giữ.\n" +
            "\n" +
            "Từ thập kỷ 70 của thế kỷ trước đến bây giờ, người lữ hành số 2 vẫn cô độc trong cuộc lữ hành, trong Vũ Trụ mênh mông, nó chỉ như một hạt bụi nhỏ bé. Nguồn truyện: Truyện FULL\n" +
            "\n" +
            "Có thể cũng như phần lớn các tàu thăm dò vũ trụ khác, hoặc đã phát sinh trục trặc, hoặc bị gián đoạn tín hiệu liên lạc vĩnh viễn biến mất trong vũ trụ cô quạnh.\n" +
            "\n" +
            "Ba mươi mấy năm qua đi rồi, khoa học kỹ thuật đang không ngừng phát triển, nhân loại đã nghiên cứu chế tạo ra nhiều tàu thăm dò vũ trụ tiên tiến hơn, có thể tương lai không xa đối với việc thăm dò tinh không sẽ tiến thêm được một bước phát triển.\n" +
            "\n" +
            "Nhưng ngay cả như vậy, tại trong khoảng thời gian khá dài, tàu thăm dò vũ trụ kiểu mới vẫn như cũ không cách nào đuổi theo bước tiến của người lữ hành số 2.\n" +
            "\n" +
            "33 năm đi qua rồi, người lữ hành số 2 đã cách Địa Cầu tới 14 tỷ Km (140 ức)\n" +
            "\n" +
            "Thời khắc này, tốc độ của nó đã đạt đến tốc độ cấp 3 của vũ trụ, trong quỹ đạo phi hành của nó, đã không bao giờ có thể quay lại Thái Dương hệ, đãtrở thành một con tàu tinh tế vũ trụ.\n" +
            "\n" +
            "Trong hắc ám và băng lãnh của Vụ trụ, điểm một vài tinh cầu, trông như những viên Kim Cương trong suốt, khảm trên một tấm màn màu đen.\n" +
            "\n" +
            "Tàu thăm dò vũ trụ Người lữ hành số 2 tuy rằng đang phi hành cực nhanh, thế nhưng trong u lãnh cùng vô ngần của vũ trụ nó chỉ như những con sâu, con kiến nho nhỏ đang chậm rãi bò trên mặt đất đen tối.\n" +
            "\n" +
            "Sau hơn 30 năm qua đi, hôm nay - giờ khắc này, Người lữ hành số 2 có phát hiện kinh người!\n" +
            "\n" +
            "Trong vũ trụ cô quạnh, chín bộ thi thể khổng lồ lặng lẽ nằm ở một nơi nào đó...\n" +
            "\n" +
            "Ngày 22 tháng 5 năm 2010, Cục Du Hành Vũ Trụ Mỹ tiếp nhận một số liệu thần bí do Người du hành Vũ trụ số 2 gửi về, sau bao nhiêu khó khăn để phiên dịch, bọn họ cũng thấy được một một hình ảnh không thể tưởng tượng.\n" +
            "\n" +
            "Trong giờ khắc này, mọi người ở trong phòng điều khiển của Cục du hành Vũ Trụ đều kinh ngạc, đứng im không nhúc nhích như những bức tượng gỗ, bọn họ đang khiếp sợ tới tột đỉnh!\n" +
            "\n" +
            "Thật lâu sau mọi người mới hồi phục tinh thần, sau đó, trong phòng điều khiển mới ồn ào.\n" +
            "\n" +
            "\"Thượng Đế ơi, mình nhìn thấy gì thế này?\"\n" +
            "\n" +
            "\"Điều này làm sao có thể, không thể tin tưởng được!\"\n" +
            "\n" +
            "...\n" +
            "\n" +
            "Người lữ hành số 2 từ lâu đã không được chỉ đạo, chỉ có thể tiến thẳng về phía trước, sau khi truyền được nhóm số liệu thần bí, lại vội vã bay qua mảng không gian đen tối, càng hướng sâu thêm vào tinh vực u ám.\n" +
            "\n" +
            "Bởi phiến tinh không này quá xa xôi, ngay cả khi có phát hiện trọng đại, thu được một hình ảnh chấn động, thì nhân loại bây giờ cũng bất lực.\n" +
            "\n" +
            "Nhóm này thần bí tin tức cũng không có công bố ra bên ngoài. Mà sau đó không lâu, người lữ hành số 2 xảy ra trục trặc, gián đoạn rồi cùng Địa Cầu tín hiệu liên lạc.\n" +
            "\n" +
            "Đến lúc này người ta đã có thể đặt một dấu chấm tròn cho sự kết thúc rồi, nhưng mà có đôi khi sự việc lại không xảy ra bình thường như mọi người dự liệu.\n" +
            "\n" +
            "Mặc kệ các dụng cụ quan sát và thăm dò không gian, hay là khoa học nghiên cứu sinh mệnh và vật lý, hay là những trạm không gian quan sát tinh không có những điều kiện ưu việt.\n" +
            "\n" +
            "Từ năm 1971 Liên Xô, lần đầu tiên phóng Trạm không gian mang theo người thành công, cho tới bây giờ toàn thế giới đã phóng ra 9 cái trạm không gian.\n" +
            "\n" +
            "Ngày 11 tháng 6 năm 2010, ở thời điểm này, bên trong Trạm không gian quốc tế, mấy nhân viên du hành vũ trụ đang kinh ngạc, con ngươi nhanh chóng co rút lại.\n" +
            "\n" +
            "Cho đến ngày nay, sự tồn tại của Thần, từ lâu đã bị phủ định. Nếu như tín ngưỡng này vẫn có người kế tục, thì cũng chỉ là trong tâm linh hư không của con người tìm kiếm một sự ký thác mà thôi.\n" +
            "\n" +
            "Thế nhưng đúng vào lúc này, tư tưởng của mấy người du hành vũ trụ tinh anh đang hứng chịu đả kích mãnh liệt, bọn họ thấy được một bức hình ảnh không thể tưởng tượng.\n" +
            "\n" +
            "Bên ngoài Trạm không gian quốc tế, bên trong băng lãnh cùng hắc ám của vũ trụ, chín cái quái vật khổng lồ không nhúc nhích, phảng phất như từ xa xư đã nằm ở đó, khiến người ta cảm giác được sự thê lương vô tận, mà đây lại là chín bộ Long thi (xác rồng)!\n" +
            "\n" +
            "Cùng rồng ở trong thần thoại truyền thuyết không khác nhau chút nào.\n" +
            "\n" +
            "Mỗi bộ Long thi (xác rồng) đều dài đến trăm mét, giống như nước thép đúc mà thành, tràn đầy sự chấn động trong cảm giác.\n" +
            "\n" +
            "Chín bộ Long thi (xác rồng) đều là Ngũ trảo Hắc Long, ngoại trừ Sừng rồng trong suốt long lanh, ánh tím lóng lánh ở ngoài, thân rồng hiện lên hắc sắc, ô quang nhấp nháy, lân phiến (vảy rồng) trong bóng đêm lóe ra một vài thần bí quang hoa.','https://doctruyenchufull.com/upload/1661150560810-gia-thien.png',0)";
    private String SQLQuery88 = "INSERT INTO truyen VALUES (null,'Đế bá','Lý Thất Dạ trèo lên trên núi, gió ban đêm thổi vừa mạnh vừa lạnh, nhưng lúc này nó lo lắng đến toát mồ hôi ướt đẫm cả người. Một đứa bé ở độ tuổi mười ba như Lý Thất Dạ, dùng tay chân một mình trèo lên dãy núi, quang cảnh đó giữa màn đêm toát ra một nét rất quái dị, khiến người ta sởn hết cả gai ốc nếu như được chứng kiến.\n" +
            "\n" +
            "Mặc dù màn đêm tĩnh lặng rất đáng sợ, nhưng trong lòng của Lý Thất Dạ lại nóng như lửa đốt.\n" +
            "\n" +
            "Lý Thất Dạ sinh ra trong một gia đình tá điền, ba mẹ đều là người nghèo khổ, từ lúc bảy tuổi nó đã bắt đầu đi chăn dê thuê.\n" +
            "\n" +
            "Lý Thất Dạ mang họ Lý theo gia đình, bởi vì lúc sinh ra đời nó khóc bảy ngày bảy đêm nên bị đặt tên là Lý Thất Dạ.\n" +
            "\n" +
            "Hôm nay, Lý Thất Dạ vẫn chăn dê như bình thường, nào ngờ, lúc chạng vạng tối đuổi đàn dê trở về, phát hiện thấy thiếu một con dê đầu đàn. Nó rất lo lắng, vội vàng trở lại núi tìm kiếm. Thế nhưng, Lý Thất Dạ tuy đã lật tung toàn bộ dãy núi mà vẫn không có tìm được con dê này.\n" +
            "\n" +
            "Nghĩ đến lão địa chủ Trương Đại Hộ hung ác, giờ lại đánh mất đi một con dê của lão ta, Lý Thất Dạ cảm thấy lòng mình bối rối hoảng loạn vô cùng.\n" +
            "\n" +
            "Sau khi đã tìm khắp cả dãy núi mà vẫn không thấy bóng dáng con dê kia đâu, bất chợt, Lý Thất Dạ nghĩ tới một chỗ, chỉ có một địa phương duy nhất mà nó chưa có đi tới tìm. Tiên Ma Động!\n" +
            "\n" +
            "Ngẩng đầu nhìn về Tiên Ma Động nằm ở phía trước, hang núi đó ở giữa màn đêm tựa như một đầu hung thú từ thời Hồng Hoang, mở to cái miệng tưởng muốn ăn tươi nuốt sống người ta bất cứ lúc nào, bên tai lại nghe thấy tiếng sói hú như quỷ kêu vọng lại, Lý Thất Dạ không khỏi rùng mình một cái.\n" +
            "\n" +
            "Tiên Ma Động vốn được coi là một nơi đầy hung ác tại địa phương này, truyền thuyết kể lại rằng ở đó một con ác ma, bất kỳ người nào đi vào đều sẽ bị nó ăn hết. Từ xưa tới nay, sau khi đi vào, chưa từng có ai có thể còn sống đi ra.\n" +
            "\n" +
            "Lúc này, âm thanh tiếng roi da quất “chát, chát“ của lão Trương Đại Hộ lại quanh quẩn bên tai của Lý Thất Dạ. Nếu mất đi một con dê, lão Trương Đại Hộ nhất định sẽ quất nó da thịt nát bươm.\n" +
            "\n" +
            "Chỉ nghĩ tới đây, Lý Thất Dạ cắn chặt răng, đi về hướng Tiên Ma Động ở phía trước mặt. Trong chớp mắt, thân ảnh của nó đã biến mất trong màn đêm.','https://123truyenz.com/images/2019/07/de-ba.jpeg',0)";

    private String SQLQuery8888 = "INSERT INTO truyen VALUES (null,'HỒN CHỦ',' Dương Đại, phải đuổi theo ta ngươi mới có thể sống sót!Nhìn thiếu nữ hoa quý đang khích lệ chính mình, Dương Đại ngơ ngác.Tiểu tỷ tỷ, ngươi là ai?Dương Đại không khỏi nhìn khắp chung quanh, đây là giảng đường một trường cấp 3, hơn năm mươi người mặc đồng phục học sinh đang xếp hàng ở chính giữa hai dãy bàn, bên cạnh bảng đen có treo một cái lịch ngày, viết thời gian đếm ngược còn lại trước kỳ thi đại học, còn lại ba mươi sáu ngày.Hắn không khỏi nhìn về phía thân thể mình, gầy hơn, trên người cũng mặc đồng phục.Đầu óc hắn lại loạn thành một đống hồ nhão.Hắn đang nằm mơ sao?Tối hôm qua, hắn làm việc quá mệt mỏi, về nhà lướt TikTok, lướt tới một nội dung, hắn lại mơ mộng chính mình có thể trở lại thời học sinh.\n" +
            "\n" +
            "Bỗng nhiên hắn bừng tỉnh tiếp tục lướt video ngắn, kết quả hôm sau thức dậy, hắn thật sự trùng sinh rồi?Cái này...Quá sung sướng đi!Dương Đại véo mình một cái, rất đau, khiến hắn càng thêm hưng phấn.Hắn dần dần nhận ra bạn học chung quanh, hẳn mình không xuyên qua mà là trùng sinh, nhưng hắn không nhớ rõ thời cấp 3 hắn từng theo đuổi ai!Vị thiếu nữ hoa quý phía trước cũng chưa từng xuất hiện trong trí nhớ của hắn.\n" +
            "\n" +
            "\n" +
            "Quan sát một hồi, hắn cảm thấy nơi này là thế giới song song, bởi vì có không ít bạn học hắn vốn không quen biết.Chờ chút!Không thích hợp!- Ông trời phù hộ, ta nhất định phải rút được thiên phú cấp B!- Thiên phú cấp B? Ngươi rút được cấp D đã không tệ rồi!- Ta chỉ muốn sống sót...- Nghe nói tháng trước tỉ lệ người mới sống sót ở 【 Thâm Vực 】 không đến sáu mươi phần trăm.- Nói cách khác lớp chúng ta phải chết hai mươi đồng học?- Nói hươu nói vượn, im miệng!...Nghe đồng học chung quanh thảo luận, Dương Đại hoang mang, Thâm Vực là thứ đồ gì?Thời cấp ba, hắn chưa từng nghe nói tới chuyện này.Hắn bỗng trừng to mắt, bởi có một nam sinh mới đứng trước bục giảng đột nhiên biến mất, thật sự là đột nhiên tan biến, hắn tuyệt đối không nhìn lầm.Hắn thấy trên giảng đài có một tảng đá màu tím, lớn chừng quả bóng rổ, phía sau bục giảng thì có ba lão sư đang đứng, vẻ mặt đều hết sức nghiêm túc.\n" +
            "\n" +
            "Cả lão sư và các bạn học đều không thấy kỳ lạ vì nam sinh kia đột nhiên tan biến.Dương Đại nhịn không được kéo thiếu nữ hoa quý trước mắt lại, hỏi: - Sao hắn lại biến mất?Thiếu nữ hoa quý quay đầu, nàng có làn da tuyết trắng, con mắt rất lớn, lông mi rất dài, đầu cột tóc đuôi ngựa, nếu bình tĩnh xem xét nàng tuyệt đối có thể tính là giáo hoa, thế nhưng hiện tại Dương Đại đang hoảng hốt hết sức, không rảnh quan tâm nhan sắc của đối phương, chỉ muốn tranh thủ thời gian làm rõ tình huống trước mắt.- Đương nhiên là tới Thâm Vực, Dương Đại, sợ hãi cũng vô dụng, bình tĩnh lại, đến Thâm Vực rồi ngươi phải nghĩ biện pháp sống sót, chỉ cần không làm loạn, tỷ lệ sống sót vẫn rất lớn.','https://img.daoquan.vn/get/images/doctruyen/images/avt.jpg',0)";
    private String SQLQuery88888 = "INSERT INTO truyen VALUES (null,'Tiên Nghịch','Thiết Trụ ngồi ở bên con đường nhỏ trong thôn, vẻ mặt ngẩn ngơ nhìn bầu trời xanh thẳm, Thiết Trụ vốn không phải là tên thật của hắn, mà là từ bé bởi vì thân thể gầy yếu, phụ thân sợ nuôi không được, vì thế dựa theo tập tục mà gọi tên mụ.\n" +
            " \n" +
            "Hắn vốn tên là Vương Lâm, họ Vương ở trong vài cái thôn xóm xung quanh xem như danh gia, tổ tiên xuất thân thợ mộc, nhất là ở thị trấn, gia tộc họ Vương cũng coi như rất có danh tiếng, có được mấy cửa hiệu chuyên môn bán sản phẩm gỗ.\n" +
            " \n" +
            "Phụ thân Thiết Trụ là con thứ vợ lẻ bên trong gia tộc, không được phép tiếp quản việc quan trọng của gia tộc, mà là ở sau khi thành hôn rời đi thị trấn, định cư tại thôn trang này.\n" +
            " \n" +
            "Bởi vì có một tay thợ mộc tinh sảo, gia cảnh Thiết Trụ cũng coi như bậc trung, ăn mặc không lo, cho dù là ở trong thôn, cũng được nhiều người tôn kính.\n" +
            " \n" +
            "Thiết Trụ từ nhỏ đã cực kỳ thông minh, yêu thích đọc sách, có rất nhiều ý nghĩ, hầu như được trong toàn thôn công nhận là thần đồng, phụ thân mỗi lần nghe được người ta khen ngợi, nếp nhăn trên mặt phụ than đều hiện ra, lộ ra nụ cười thoải mái.\n" +
            " \n" +
            "Mẫu thân càng hay quan tâm đối với hắn hắn, có thể nói từ nhỏ đến lớn, Thiết Trụ đều là sinh hoạt trong sự yệu thương của cha mẹ, hắn biết cha mẹ đối hắn kỳ vọng rất cao, đứa trẻ nhà khác ở hắn tuổi đều đã ra đồng làm nông kiếm sống rồi, nhưng hắn lại ở nhà đọc sách.\n" +
            " \n" +
            "Đọc sách nhiều, ý nghĩ tự nhiên cũng sẽ theo đó mà đến, đối với thế giới bên ngoài sơn thôn, hắn tràn ngập hướng tới. Ngẩng đầu nhìn phía cuối con đường nhỏ, Thiết Trụ thở dài một tiếng, khép lại sách trong tay, đứng dậy đi về nhà.\n" +
            " \n" +
            "Phụ thân ngồi ở trong sân, trong tay cầm tẩu thuốc, sau khi hút thật sâu một ngụm, nói với Thiết Trụ vừa đẩy cửa vào:\n" +
            " \n" +
            "- Thiết Trụ, đọc sách thế nào?\n" +
            " \n" +
            "Thiết Trụ ứng phó vài câu, phụ thân gõ gõ tẩu thuốc, đứng dậy nói:\n" +
            " \n" +
            "- Thiết Trụ a, ngươi phải đọc sách cho thật tốt, sang năm chính là kỳ thi lớn trong huyện, ngươi về sau có tiền đồ hay không, thì phải xem lần này rồi, nhưng đừng giống ta, cả đời này vẫn ở lại trong thôn, ôi.\n" +
            " \n" +
            "- Được rồi, ông mỗi ngày cằn nhằn, phải để ta nói, Thiết Trụ nhà chúng ta nhất định có thể thi đỗ.\" Mẫu thân Thiết Trụ, bưng đồ ăn đặt ở trên bàn trong sân, gọi cha con hai người bọn họ lại ăn cơm.','https://i1.wp.com/gioitienhiep.com/wp-content/uploads/2019/09/210a4af1c839a9b8e7dc73e32c1ef63e.jpg?w=840&ssl=1',0)";
    private String SQLQuery001 = "INSERT INTO truyen VALUES (null,'ĐL ĐẠI LỤC','Ba Thục còn có mỹ danh Thiên Phủ Chi Quốc, trong đó nổi danh nhất chính là Đường Môn\n" +
            "\n" +
            "Đường Môn là một thần bí địa phương, rất nhiều người chỉ biết đó là một địa điểm giữa sườn núi, mà đỉnh núi nơi Đường Môn tọa lạc lại có một cái tên làm kẻ khác đảm chiến kinh tâm - Quỷ Kiến Sầu.\n" +
            "\n" +
            "Từ đỉnh Quỷ Kiến Sầu mà ném ra một hòn đá, phải đến 19 mới nghe được tiếng vang của hòn đá va chạm dưới chân núi, có thể thấy được núi cao thế nào, cũng bởi vì mười chín giây này, vượt qua mười tám tầng địa ngục một bậc, nên mới có cái tên này.\n" +
            "\n" +
            "Một gã hôi y (áo xám) thanh niên đang đứng trên đỉnh núi Quỷ Kiến Sầu, gió núi mãnh liệt không làm thân thể hắn di động chút nào, từ trên ngực hắn ngực có một chữ Đường lớn có thể nhận ra, hắn đến từ Đường Môn, áo xám đại biểu là Đường Môn ngoại môn đệ tử.\n" +
            "\n" +
            "Hắn năm nay hai mươi chín tuổi, xuất sanh không lâu thì tiến vào Đường Môn, trong ngoại môn bài danh đệ tam, bởi vậy ngoại môn đệ tử xưng hắn một tiếng Tam Thiếu. Đương nhiên, tới miệng nội môn đệ tử miệng, tựu biến thành Đường Tam.\n" +
            "\n" +
            "Đường Môn từ khi thành lập thì bắt đầu chia làm nội ngoại nhị môn, ngoại môn đệ tử đều là ngoại tính (họ khác) hoặc được thụ dư (ban cho) Đường tính (họ Đường), mà nội môn là Đường Môn trực hệ gia tộc truyền thừa.\n" +
            "\n" +
            "Lúc này, vẻ mặt Đường Tam rất phong phú, khi thì cười, khi thì khóc, nhưng vô luận thế nào, đều không thể che dấu hưng phấn phát ra từ nội tâm.\n" +
            "\n" +
            "Hai mươi chín năm, hai mươi chín năm trước hắn được ngoại môn trưởng lão Đường Lam thái gia nhặt về Đường Môn từ lúc còn nằm trong tã, Đường Môn chính là nhà hắn, mà Đường Môn ám khí chính là tất cả của hắn.\n" +
            "\n" +
            "Đột nhiên, Đường Tam sắc mặt chợt biến đổi, nhưng rất nhanh trở lại bình thường, có chút khổ sáp đích tự nhủ: \"Cái gì phải tới cuối cùng sẽ tới. \"\n" +
            "\n" +
            "Mười bảy đạo thân ảnh, mười bảy đạo bạch sắc thân ảnh, tựa như ánh sao toát ra từ sườn núi hướng đỉnh núi mà đến, chủ nhân mười bảy đạo thân ảnh này, tuổi nhỏ nhất cũng ngoại ngũ tuần (hơn 50), mỗi người đều thần sắc ngưng trọng, bọn họ mặc bạch y đại biểu chính là nội môn, mà chữ Đường màu vàng trước ngực là tượng trưng cho Đường Môn trưởng lão.\n" +
            "\n" +
            "Đường Môn nội môn trưởng lão đường kể cả chưởng môn Đường Đại tiên sinh tổng cộng có mười bảy vị trưởng lão, lúc này đăng sơn (lên núi) cũng là mười bảy vị. Cho dù là võ lâm đại hội cũng không thể kinh động toàn bộ Đường Môn trưởng lão đồng thời xuất động, phải biết rằng, trong số Đường Môn trưởng lão, người lớn tuổi nhất đã vượt qua hai giáp (hơn 120 tuổi).\n" +
            "\n" +
            "Đường Môn trưởng lão tu vi, không ai không đạt tới cực cảnh, chỉ trong chớp mắt, bọn họ cũng đã đi tới đỉnh núi.','https://i0.wp.com/hhhkungfu.tv/wp-content/uploads/2022/01/Dau-La-Dai-Luc.jpg?fit=320,449&ssl=1',0)";
    private String SQLQuery002 = "INSERT INTO truyen VALUES (null,' Kiến Long','Trên sơn đạo Tĩnh Đốc Sơn ánh mặt trời ôn hòa, phong cảnh hợp lòng người, ba thân ảnh sóng vai đi tới.\n" +
            "\n" +
            "Thanh niên dáng người đỉnh bạt đi ngoài cùng bên trái dặn dò: \"A Man, đây không phải là ở nhà, lại đẹp đẽ cũng vô ích, không có ai nhường ngươi, thu thu tính tình của ngươi, sau khi vào Huyền Diệu Môn, dốc lòng tu học.\n" +
            "\n" +
            "Đây là cơ hội vất vả lắm mới chiếm được, cha và đại ca đều vô cùng vui mừng, ngươi không nên tùy hứng làm bậy, cố ý quậy phá.\"\n" +
            "Cố Phù Du khoanh tay, hỏi ngược lại: \"Vui mừng? Hai người bọn họ là vui mừng rốt cuộc cũng bỏ được cái phiền phức này thôi.\"\n" +
            "Nàng thiếu kiên nhẫn lầm bầm: \"Cố Hoài Ưu, ta không muốn đi Huyền Diệu Môn.\"\n" +
            "Bên trong tiên môn luôn luôn nhiều quy củ, nàng thực không thích bị quản thúc.\n" +
            "\n" +
            "Cố Phù Du từ giữa đi ra, xoay người lại, đối mặt với hai người từng bước một đi ngược lại: \"Hai ngươi quả thật là có bản lĩnh để vào Huyền Diệu Môn, ta vào Huyền Diệu Môn tính là cái chuyện gì, đi vào để người ta xem thường sao.\"\n" +
            "\"Ngươi còn nói mê sảng.\" Cố Hoài Ưu trời sinh một gương mặt dễ nói chuyện, không nhiều thu hút, để vào sư môn, hắn trông có vẻ trang trọng, mặc vào một thân thanh bào cổ tròn, tóc dài cột quan, đem mấy phần tuấn tú của hắn đều câu ra.\n" +
            "\"Ngươi đừng đánh mưu ma chước quỷ, lần này lại quậy phá, trở về thành Tiêu Dao, coi chừng thế bá đánh gãy chân ngươi.\" Tư Miểu nhấc lên mí mắt miễn cưỡng liếc Cố Phù Du một chút, vô tình chặt đứt tính toán của nàng.\n" +
            "Tư Miểu mặc một thân váy lụa màu tím nhạt, cài tóc bằng ngọc trâm, tuấn mắt tu mi, dung mạo nghiên lệ, một cái diệu nhân, chỉ có khuyết điểm duy nhất chính là đôi môi sắc, đều nói người môi mỏng nói chuyện cay nghiệt.\n" +
            "\n" +
            "Một cái độc miệng này của Tư Miểu, nhiều năm như vậy Cố Phù Du là tràn đầy lĩnh giáo.\n" +
            "\n" +
            "Cố Phù Du ở trong lòng than, hai người này trai tài gái sắc ở bên nhau làm sao lại muốn tù nàng, thả nàng đi, liền hai người bọn họ tình chàng ý thiếp không tốt hay sao.\n" +
            "Cố Phù Du ngoài miệng tuy không nói gì nữa, trong lòng vô cùng là không muốn.\n" +
            "Cố Hoài Ưu ôn thanh an ủi: \"Huyền Diệu Môn là Tiên môn có thể đếm được trên đầu ngón tay, ngươi có thể vào để tu hành, trăm lợi mà không một hại.\n" +
            "\n" +
            "A Man, ngươi không phải thích thao túng trận pháp sao, trong môn phái có Lục Hạc trưởng lão tinh thông cổ kim trận pháp, ngươi được hắn dạy dỗ, nhất định có thể nâng cao bản lĩnh.\"\n" +
            "Cố Phù Du gương mặt cay đắng, những câu nói kia một chữ cũng không nghe lọt tai.\n" +
            "\n" +
            "Nàng bị nửa lừa gạt nửa uy hiếp lộng đến Huyền Diệu Môn, cho dù trước kia trong lòng có mấy phần hứng thú cũng sớm bị càng nhiều không thoải mái ép xuống.\n" +
            "\n" +
            "...\n" +
            "Tĩnh Đốc Sơn là Linh sơn có tiếng của Nam Châu, thế núi bằng phẳng, cây cỏ xanh um.\n" +
            "\n" +
            "Ba người đã đi tới sườn núi, đến tốp năm tốp ba đệ tử nhập môn.\n" +
            "Cố Phù Du nhìn thấy bên đường có một cây hoa tử đằng, cành hoa rất đẹp, từng chùm rũ xuống, tựa như một thác nước màu trắng tím.\n" +
            "\n" +
            "Nàng không khỏi nhìn nhiều một chút.\n" +
            "\n" +
            "Bỗng nhiên không trung có một trận gió nhẹ thổi qua, làm bay những cánh hoa màu tím tựa như lông vũ.\n" +
            "Ngẩng đầu nhìn lên, không trung ba người ngự kiếm phi hành, hướng về sơn môn mà đi.\n','https://img.wattpad.com/cover/320044844-256-k207530.jpg',0)";
    private String SQLadmin = "INSERT INTO taikhoan (tentaikhoan,matkhau,email,phanquyen) VAlUES ('admin','quyen1','admin@gmail.com',2)";
    private String SQLQuery003 = "INSERT INTO truyen VALUES (null,'HÀNG LONG QUYẾT','Đau đớn! Đây là cảm giác duy nhất của Lạc Thanh Từ sau khi ngũ quan hoạt động trở lại.\n" +
            "\n" +
            "Giống như bị một nhát búa đập vào ngực, không chỉ xương cốt đứt đoạn mà ngũ tạng cũng vỡ nát.\n" +
            "Rõ ràng nàng đã tỉnh, nhưng trước mắt vẫn là màu đen mênh mông, nàng chỉ có thể thả người bay ra ngoài không kiểm soát, sau đó tầng tầng rơi xuống đất.\n" +
            "Mùi máu tươi dày đặc từ yết hầu cuồn cuộn mà ra, Lạc Thanh Từ co giật một chút, há miệng phun ra một ngụm huyết.\n" +
            "\n" +
            "Cơn đau khó có thể ngăn chặn lan từ ngực xuống tứ chi, tàn phá ruột gan nàng.\n" +
            "\n" +
            "Đau đến nàng nói không nên lời, cũng kêu không ra tiếng.\n" +
            "\n" +
            "Nàng cảm thấy như mình sắp chết.\n" +
            "Chẳng lẽ nàng vừa xuyên đến đây, liền biến thành đá kê chân sao?\n" +
            "Trước khi nàng có thể hồi phục sau cơn đau dữ dội, một tiếng gào bi thương cùng tức giận nổ tung trên đỉnh đầu nàng, trong phút chốc một luồng kình phong nương theo âm thanh đó bao phủ mà tới.\n" +
            "Lạc Thanh Từ cố gắng đứng dậy, nhưng lại bị luồng sức mạnh kia chấn đến ngã lăn vào trong hang đá.\n" +
            "Lần này, họa vô đơn chí.\n" +
            "Tiếng gầm mạnh mẽ mà cổ xưa, như thể cá voi dùng hết sức xuyên thủng đáy biển sâu rồi kiệt lực rơi xuống, hồi quang phản chiếu.\n" +
            "Dưới tình huống này còn có thể nghĩ nhiều như vậy, Lạc Thanh Từ đối chính mình đã không còn gì để nói.\n" +
            "\"Chúc ngài nhiều may mắn!\"\n" +
            "Giữa lúc nàng đang choáng váng, một giọng nói máy móc lạnh lùng vang lên trong đầu nàng, lập tức cơn đau nhức trên cơ thể nàng từ từ biến mất, ngũ quan chết lặng cũng chậm rãi khôi phục.\n" +
            "Nàng thở hổn hển, cắn răng yếu ớt nói: \"Đây chẳng khác nào chúc ta chết sớm.\"\n" +
            "Vừa mới nói xong, nàng đã bị cảnh tượng trước mắt làm cho chấn động, tầm mắt dần trở nên rõ ràng, cuối cùng nàng cũng nhìn thấy thứ vừa phát ra tiếng gầm kh ủng bố kia.\n" +
            "Đó là một con rồng, toàn thân đầm đìa vết máu, vảy rồng giống như những viên ngọc bích màu đen, sáng lóng lánh trong đêm.\n" +
            "Rồng kia nằm rũ rượi trên đất, Long Lân trên người bị tổn hại nặng nề, vết máu loang lổ, trên cổ có một vết thương to bằng miệng bát đang không ngừng chảy máu.\n" +
            "\n" +
            "Đầu rồng khổng lồ cố sức nâng lên, râu rồng phi dương, hơi thở rồng dâng trào không chút nào kiềm chế.\n" +
            "Cho dù là kề bên tử vong, thần thái vương giả quanh thân Vua Rồng vẫn khiến người cảm thấy nể phục.\n" +
            "\n" +
            "Sau khi phát ra một tiếng rống dài, đôi mắt Rồng bao hàm phẫn nộ cùng đau lòng, tuy là gắt gao nhìn về phía Lạc Thanh Từ, thế nhưng cũng không phải đang nhìn nàng.\n" +
            "\"Nhân tộc các ngươi nhất định sẽ trả giá đắt cho tội ác ngày hôm nay!\" Trong tiếng nói khàn khàn mang theo dày đặc hận ý.\n" +
            "\n" +
            "Dứt lời, Long Vương cũng liền vô lực chống đỡ đầu rồng to lớn, ngã ầm ầm xuống đất.\n" +
            "Giữa một mảnh huyết tinh lan tràn, một giọt nước mắt rồng rơi xuống, như ngọc trai bị vùi trong biển máu.\n" +
            "Sau khi Long Vương ngã xuống, ngay lập tức đất rung núi chuyển.\n" +
            "\n" +
            "Một tràn Long ngâm từ bốn phương tám hướng bao phủ tới, như sấm sét nổ vang.\n" +
            "\n" +
            "Mỗi âm thanh đều hàm chứa bi thương, đau đớn rồi lại nghiêm nghị, như đang tống biệt Long giới Chi Chủ, để Lạc Thanh Từ nghe được trái tim không ngừng run lên.\n" +
            "Long ngâm qua đi, đá vụn bắt đầu cuồn cuộn hạ xuống, Lạc Thanh Từ nhanh chóng đứng dậy tránh né, mới chạy được vài bước đã vấp phải vật gì đó tròn tròn, làm nàng ngã lăn trên đất.\n" +
            "\n" +
            "Lần té ngã này khiến nàng không thể động đậy nữa.\n" +
            "Đợi đến tất cả bụi trần tan mất, Lạc Thanh Từ giống như người sống sót sau tai nạn, nằm trên đất kịch liệt thở dốc.\n" +
            "\n" +
            "Nàng quay đầu liếc nhìn xung quanh, may là hang đá kỳ lạ này không bị sập hoàn toàn, nhưng cửa động đã bị cự thạch đổ xuống ngăn chặn.\n" +
            "Cho dù được Hệ Thống báo trước về tình huống mà mình sẽ gặp phải, Lạc Thanh Từ vẫn bị sốc bởi những gì nàng chứng kiến đã vượt qua nhận thức của nàng rồi.','https://img.wattpad.com/cover/17806838-256-k742977.jpg',0)";
    private String SQLQuery004 = "INSERT INTO truyen VALUES (null,'THIÊN SƯ LỘ','Mệnh của tôi không tốt, vừa ra đời đã mắc bệnh, sốt cao liên tục mấy ngày, suýt nữa thì không sống nổi.\n" +
            "\n" +
            "Sau khi điều trị ở bệnh viện nửa năm trời mà vẫn không có khởi sắc, bác sĩ cũng hết cách, đành thông báo tin dữ cho bố mẹ tôi.Nhưng đúng vào thời điểm đó, có một tiên sinh biết bấm độn đi ngang qua thôn, phán rằng mệnh tôi phạm sát, đụng phải tà ma, nhất định phải xung hỉ.Người dân nông thôn không xa lạ gì với việc xung hỉ, đại khái là dùng chuyện vui để xua đi vận khí đen đủi.\n" +
            "\n" +
            "Vậy chuyện gì mới là chuyện vui? Đời người có ba cái vui, đó là đêm động phòng hoa chúc, lúc đề danh bảng vàng, và khi tha hương gặp cố tri.\n" +
            "\n" +
            "\n" +
            "Trong đó đứng đầu chính là chuyện kết hôn.Tình huống lúc ấy của tôi cũng éo le, rõ ràng là sắp không sống nổi nữa rồi, nhà nào mà mà chịu gả con gái cho? Chỉ có một gia đình bần hàn gật đầu, ông ta có một cô con gái mới hai tuổi.\n" +
            "\n" +
            "Gã này ăn ngon lười làm, người trong thôn không ai ưa.\n" +
            "\n" +
            "Tuy mẹ tôi cũng không ưng nhưng vì hết cách nên đành đồng ý, dứt khoát bỏ ra một phần lễ hỏi hậu hĩnh, kết thông gia với nhà ông ta.Nói ra cũng thật thần kì, ngay sau khi cưới cô dâu nhỏ này về, tôi thật sự giữ được mạng.\n" +
            "\n" +
            "Chỉ là người vẫn yếu ớt nhiều bệnh như trước, có bổ sung bao nhiêu thứ đồ bổ đi nữa cũng không thấy khởi sắc.\n" +
            "\n" +
            "Đến năm tôi ba tuổi thì cô dâu nuôi từ nhỏ của tôi được năm tuổi lại rơi xuống giếng chết, ba ngày ba đêm sau mới vớt được thi thể lên.Chuyện này ầm ĩ khắp cả thôn, nhưng bố mẹ tôi cũng không còn hơi sức đâu để ý đến, bởi vì lúc ấy bệnh tình của tôi chợt trở nặng.\n" +
            "\n" +
            "\n" +
            "Cùng ngày nàng dâu mất, tôi cũng bất tỉnh, ở trong bệnh viện cứu chữa một đêm vẫn sống dở chết dở, tựa như sinh mạng của tôi cũng theo cô ấy mà đi rồi.Mẹ tôi khóc đến ngất lên ngất xuống, trong thời khắc mấu chốt, bố tôi vẫn tỉnh táo hơn.\n" +
            "\n" +
            "Ông quyết định dùng cách cũ, tìm thêm một cô dâu nhỏ nữa cho tôi.\n" +
            "\n" +
            "Nhưng lúc này đã khó khăn hơn nhiều, tôi đã ba tuổi, lại có vẻ sắp chết, gia đình bình thường nào mà chịu gả con gái cho? Bố tôi đi tìm muốn mòn cả chân, cuối cùng phải bỏ ra số tiền lớn, mới dẫn được một cô bé từ thôn bên cạnh về.Cô bé này không chỉ xấu xí, mà con vừa câm vừa điếc, nhưng bố tôi cũng không chê.\n" +
            "\n" +
            "Đã đến nước này, có người chịu lấy tôi là may lắm rồi.\n" +
            "\n" +
            "Cứ thế cuộc đời tôi có hai cuộc hôn nhân bất chợt, cũng đúng như lời thầy tướng số kia nói lúc ban đầu, vận hạn của tôi phải dùng việc xung hỉ để hóa giải.Sau đám cưới thứ hai, sức khỏe của tôi quả nhiên tốt lên nhiều, chưa tới mấy ngày đã ra viện.\n" +
            "\n" +
            "\n" +
            "Hơn nữa, điều khiến cho mọi người bất ngờ là, lần này tình trạng của tôi càng ngày càng tốt, khuôn mặt nhỏ nhắn cũng dần hồng hào lên, đã không thua kém gì người bình thường.Lúc đó, tôi không thích cô dâu đã xấu xí lại vừa câm vừa điếc kia, nhưng bố mẹ không cho phép tôi bắt nạt cô bé, nói rằng cô bé có ơn cứu mạng với tôi.\n','https://sgp1.digitaloceanspaces.com/truyenonline/webtruyen-covers/story/img_url/37585/thien-su-lo-31917.jpeg',0)";
    private String SQLQuery005 = "INSERT INTO truyen VALUES (null,'Thông Linh Phi','Sư phụ nói vận mệnh không phải do trời định, vận mệnh là do tự mình nỗ lực mà thay đổi. \n" +
            "\n" +
            "\n" +
            "Một hôm, có một cậu bé chăn cừu ngồi nghỉ mát dưới gốc cây sau một ngày dài phơi mình dưới nắng cậu bé thấy người mệt mỏi và nóng bức. Một làn gió mơn man thổi thoa nhẹ lên tấm thân mỏi mệt của chú bé. Cậu bé bắt đầu thấy buồn ngủ. Vừa đặt mình xuống cậu bé bỗng ngước mắt nhìn lên những cành cây. Bấy giờ cậu bé bỗng thấy mình thật kiêu hãnh, cậu vẫn thường hay khoe với mọi người rằng cậu có tài chăn cừu và đàn cừu của cậu nhờ vậy mà lớn rất nhanh. Khi cậu bé phát hiện ra cây đa chỉ có những chùm quả rất nhỏ, nó bắt đầu thấy ngạc nhiên. Cậu bắt đầu chế giễu: hư, một cái cây to khỏe thế này mà làm sao chỉ có những bông hoa những chùm quả bé tí tẹo thế kia, mọi người vẫn bảo là cái cây này thông thái lắm kia mà nhưng làm sao nó có thể thông thái khi mà quả của nó chỉ toàn bé xíu như vậy. Dĩ nhiên là cây đa nghe hết những lời của cậu bé nhưng cây vẫn im lặng và cành lá chỉ khẽ rung rinh đủ để cho gió cất lên khúc hát ru êm dịu. Cậu bé bắt đầu ngủ, cậu ngáy o o…. Cốc.\n" +
            "Quả đa nhỏ rụng chính giữa trán của cậu bé, nó bừng tỉnh nhưng càu nhàu: “Gừm… người ta vừa mới chợp mắt được có một tí”, rồi nó nhặt quả đa lên chưa hết chưa biết định làm gì với quả đa này bỗng nhiên cậu bé nghe thấy có tiếng cười khúc khích, cậu nghe thấy cây hỏi:\n" +
            "– Có đau không ?.\n" +
            "– Không nhưng mà làm người ta mất cả giấc ngủ .\n" +
            "– Đó là bài học cho cậu bé to đầu đấy. Cậu chẳng vừa nhạo tôi là chỉ sinh ra toàn những quả nhỏ xíu là gì.\n" +
            "– Tôi nhạo đấy tại sao người đời lại bảo bác là thông thái được nhỉ? Phá giấc ngủ trưa của người khác! Thế cũng là thông minh chắc!.\n" +
            "Cây cười và nói: này này anh bạn anh hãy nghe đây những chiếc lá của tôi cho cậu bóng mát để cậu lấy chỗ nghỉ ngơi. Ừ thì cứ cho là quả của tôi nó bé đi chăng nữa nhưng chẳng lẽ cậu không thấy rằng tạo hóa hoạt động rất hoàn chỉnh đó sao. Cậu thử tưởng tượng xem, nếu quả của tôi to như quả dừa thì điều gì sẽ xảy ra khi nó rơi vào đầu cậu.\n" +
            "Cậu bé im thin thít: ừ nhở. Cậu chưa hề nghĩ đến điều này bao giờ cả.\n" +
            "Cây lại nhẹ nhàng tiếp lời:\n" +
            "– Những người khiêm tốn có thể học hỏi rất nhiều điều từ việc quan sát những vật xung quanh đấy cậu bé ạ.\n" +
            "– Vâng bác đa bác cứ nói tiếp đi.\n" +
            "– Cậu hãy bắt đầu làm bạn với những gì ở quanh cậu. Chúng ta tất cả đều cần tới nhau. Cậu cứ nhìn bầy ong kia mà xem. Nhờ có ong mà hoa của tôi mới có thể trở thành quả. Thế còn bầy chim kia thì sao. Chúng làm tổ ngay giữa tán lá của tôi đây này. Những con chim bố mẹ kia phải làm việc vất vả cả ngày để bắt sâu nuôi con và cậu có biết việc làm đó có ý nghĩa gì với tôi không?.\n" +
            "– Không, có ý nghĩa gì vậy hả bác?.\n" +
            "– Sâu ăn lá chính vậy loài chim kia chính là những người bạn của tôi. Chúng còn giúp cả cậu nữa đấy, sở dĩ cừu của cậu có đủ lá và cỏ để ăn là vì chim chóc đã tiêu diệt hết các loài côn trùng và sâu bọ. Và chưa hết đâu cậu bé ạ!.\n" +
            "– Còn gì thế nữa hả bác đa.\n" +
            "– Cậu hãy nhìn xuống chân mình mà xem, những chiếc lá rụng tạo thành lớp thảm mục, những con sâu đào đất ngoi lên để ăn lá, chúng đào đất thành những lỗ nhỏ, nhờ đó không khí có thể vào được trong đất. Có không khí trong đất nên bộ rễ của tôi mới khỏe thế nào đấy. Rễ khỏe nên tôi cũng khỏe hơn. Nào thế bây giờ cậu trẻ đã hiểu chưa?.\n" +
            "– Cháu hiểu rồi thưa bác. Bác tha lỗi cho cháu nhé vì đã cười nhạo bác bác đa ạ.\n" +
            "– Không sao bây giờ cháu hãy ra dắt cừu về đi.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Có thể cậu bé chăn cừu không phải ngay sau đó sẽ trở nên khiêm tốn, học hỏi luôn được nhưng rõ ràng là cậu đã nhận ra người ta không thể sống lẻ loi được.','https://1.bp.blogspot.com/-mtWAbcdmhK4/XnzJZIvIUKI/AAAAAAACUnk/HokdEB8D3DsZwqSdnRoVD9WUDwuHn-DawCLcBGAsYHQ/s0/3.jpg',0)";
    private String SQLQuery006 = "INSERT INTO truyen VALUES (null,'PHÀM NHÂN TU TIÊN','Tại thời điểm trước khi Mặc đại phu trở về sơn cốc, Hàn Lập biết sử dụng cái bình này trong thần thủ cốc tạm thời an toàn, bởi vì cả sơn cốc cũng chỉ có mình hắn, bình thường cũng không có ngoại nhân tùy tiện xông vào trong cốc, cam đoan ở đoạn thời gian này cũng không gặp cái gì ngoài ý muốn, có thể yên tâm lớn mật sử dụng bình nhỏ.\n" +
            "\n" +
            "Hàn Lập bắt đầu đoán thời gian Mặc đại phu trở về sơn cốc, cảm giác được nếu hắn tại những địa phương phụ cận tìm thì không có khả năng tìm được dược tài tốt, hắn sợ rằng phải đi những địa phương khá xa mà tìm, rất có thể là phải đi sâu vào những nơi vắng vẻ không người, hoặc vào sâu trong rừng rậm tìm kiếm. Chỉ có những nơi hẻo lánh như vậy mới có hy vọng hái được một ít hi hữu dược tài. Nhưng thời gian trên đường đi là thứ nhất, hơn nữa còn thời gian tốn hao để sưu tầm dược tài, ít nhất cũng phải gần một năm mới có thể trở về cốc.\n" +
            "\n" +
            "Bây giờ cách thời gian Mặc đại phu rời cốc khoảng nửa năm, phỏng chừng hắn có khoảng sáu đến bảy tháng nữa mới về được Thất huyền môn. Trước khi hắn trở vè, Hàn Lâpk chỉ có thể cố gắng nuôi dưỡng một số thảo dược hữu dụng với chính mình, phải có kế hoạch dựa theo mấy cách điều chế dược tài trân quý mà hắn biết được, không thể lãng phí thứ lục dịch này.\n" +
            "\n" +
            "Hàn Lập sắp chế tác những dược vật có thể tăng trưởng công lực, giúp mình đột phá bình cảnh. Đây đều là những loại mà Mặc đại phu trước đây muốn phối chế, nhưng lại không tìm đủ dược tài cần thiết, mấy loại dược tài này đều là những loại để người bình thường táng gia bại sản, là bảo vật người giang hồ liều mạng tranh đoạt.\n" +
            "\n" +
            "Chính như là y thuật cao minh như Mặc đại phu, cũng chưa bao giờ ra mắt một trong những loại dược tài này, chứ đừng nói là thân thủ phối chế. Phải biết rằng Mặc đại phu mặc dù nắm giữ phương pháp phối chế thánh dược, nhưng trong tay không có dược tài, cũng chỉ có thể ngửa mặt lên trời thở dài.\n" +
            "\n" +
            "Hàn Lập lúc trước theo Mặc đại phu học y thuật, đối với cách điều chế hi hữu này cảm thấy hứng thú, hắn mặc dù không hy vọng xa vời chính mình có thể phối chế được dược vật này, nhưng cũng đem cách điều chế ghi nhớ không ít. Mặc đại phu đối với việc học tập cách điều chế này của hắn mang một thái độ bình thường. Chỉ cần Hàn Lập hỏi, hắn sẽ trả lời đầy đủ cho Hàn Lập, không có chút dấu diếm. Đại khái Mặc đại phu cho rằng cách điều chế này thuộc về loại trân quý nhưng không thể sử dụng được.\n" +
            "\n" +
            "Bây giờ cách điều chế đã trở thành máu thịt của Hàn Lập. Hắn chiếu theo đúng phương pháp để nuôi dưỡng 5 loại thảo dược, không dám có một chút buông lỏng. Phải biết rằng thời gian của hắn cũng không nhiều, hắn phải điều chế tốt dược vật này trước khi Mặc đại phu trở về, sau đó thì đem cái bình giấu đi, quyết không sử dụng trên ngọn núi này lần nữa.\n" +
            "\n" +
            "Hàn Lập cũng không có chút tin tưởng có thể ở trước mặt Mặc đại phu sử dụng bình nhỏ mà không lộ ra dấu vết. Trong lòng hắn rất rõ ràng Mặc đại phu là người khôn khéo cẩn thận cỡ nào. Hắn cũng không có ý định đem bí mật về bình nhỏ nói cho Mặc đại phu.\n" +
            "\n" +
            "Hàn Lập cảm giác được chính mình và Mặc đại phu có quan hệ rất kì lạ, không phải như quan hệ thầy trò bình thường.\n" +
            "\n" +
            "Mặc đại phu thường xuyên dùng một loại ánh mắt kì lạ nhìn hắn, điều này làm cho Hàn Lập có cảm giác đối phương đang ẩn giấu một bí mật có hại cho mình. Đặc biệt là một, hai năm gần đây, loại cảm giác này của Hàn Lập càng thêm mãnh liệt. Điều này làm cho Hàn Lập và Mặc đại phu không có cách nào giống như thầy trò bình thường thân mật không khoảng cách, không có chuyện gì sẽ nói.\n" +
            "\n" +
            "Mặc đại phu lúc ngày thường kì thật đối với hắn phi thường tốt. Không có đấm đá, không bao giờ mở miệng mắng to, trên phương diện tu luyện khẩu quyết cũng không tiếc sức lực tạo cho hắn điều kiện tốt nhất, nhưng giữa hai thầy trò như tồn tại một tầng bình chướng, luôn có một loại không khí miễn cưỡng chung quanh hai người phiêu đãng.\n" +
            "\n" +
            "Mặc đại phu rất rõ ràng ý thức được sự tồn tại của vết rách này, nhưng mà hắn không có một chút ý định nào để bổ cứu tình thầy trò này, vẫn làm y như trước, chỉ một mặt đốc xúc tiến độ khẩu quyết Hàn Lập tu luyện. Nhưng mà khi nhìn về phía Hàn Lập, loại cổ quái ánh mắt này tựa hồ dần dần mất đi, thậm chí đã một thời gian dài rồi không có ánh mắt này nữa.\n" +
            "\n" +
            "Nhưng giác quan thứ sáu của Hàn Lập mơ hồ nói cho chính mình biết, đối phương cũng không thật sự buông tha ý đồ với chính mình, mà là rất xảo diệu đem dục vọng của hắn che dấu. Cứ như vậy làm Hàn Lập đối với Mặc đại phu tăng thêm vài tầng đề phòng. Dưới tình huống như thế này, hắn sao dám đem bí mật của bình nhỏ cho đối phương biết chứ!','https://i.pinimg.com/236x/20/60/20/20602066da66ba7981b559ea079d6333.jpg',0)";
    private String SQLQuery007 = "INSERT INTO truyen VALUES (null,'NGUYÊN TÔN','Bên trong nội điện đèn đuốc sáng trưng, xa hoa tráng lệ, khí thế uy nghiêm, bên trong điện có thắp trường minh đăng, bên trong đang đốt một viên đá xanh, khói xanh lượn lờ bay lên, vấn vít trong điện.\n" +
            "\n" +
            "Đó chính là thanh đàn thạch, khi đốt cháy sẽ tỏa ra một hương thơm lạ lùng, có hiệu quả ngưng thần tĩnh khí, là vật cần chuẩn bị cho những lúc tu luyện, bất quá giá cả của thứ này cũng không rẻ, có thể dùng nó như một loại hương liệu thế này, đủ để nói rõ chủ nhân của nơi đây có địa vị không tầm thường.\n" +
            "\n" +
            "Bên trong nội điện, một nam tử trung niên thân mặc y phục màu vàng sáng đang chắp tay đứng, hắn có gương mặt kiên nghị, trong mắt lộ ra vẻ uy nghiêm, rõ ràng có địa vị cao, mà sau lưng của hắn thì ẩn ẩn có khí tức bốc lên, tựa như lửa cháy lại như sấm dậy, phát ra tiếng nổ vang trầm thấp.\n" +
            "\n" +
            "Chẳng qua, nếu như nhìn về phía cánh tay phải của hắn thì sẽ phát hiện nó trống không, bởi vì đã cụt một tay.\n" +
            "\n" +
            "Còn có một mỹ phụ vận cung trang đứng bên cạnh hắn, nàng có thân hình mảnh mai, dung mạo xinh đẹp lại thong dong, bất quá trên mặt nàng vẫn lộ ra vẻ suy yếu nhợt nhạt.\n" +
            "\n" +
            "Mà lúc này, dù bản thân có địa vị không thấp, nhưng trên gương mặt của hai người đều có phần lo lắng bất an, đưa mắt nhìn về phía trước, chỉ thấy ở trên giường có một thiếu niên tuổi chừng mười ba mười bốn tuổi đang ngồi xếp bằng, thân hình của thiếu niên có vẻ gầy gò, hai mắt nhắm nghiền, trên gương mặt non nớt đáng lẽ nên có vẻ sáng sủa phấn chấn của một thiếu niên, ấy thế mà vẫn bị một cỗ huyết khí quấn quanh.\n" +
            "\n" +
            "Cổ huyết khí quỷ dị kia chạy dọc bên dưới làn da của hắn, ẩn ẩn tựa như còn có tiếng rồng ngâm oán độc truyền ra.\n" +
            "\n" +
            "Mà nương theo tiếng rồng ngâm kia, gân xanh trên trán của thiếu niên cũng giật mạnh, thân thể run lên bần bật, gương mặt trở nên vặn vẹo, giống như đang phải gánh chịu nổi thống khổ khó mà nói rõ.\n" +
            "\n" +
            "ở bên cạnh thiếu niên, một vị lão giả đầu tóc bạc phơ cầm một chiếc gương đồng trong tay, bên trên gương đồng phát ra quang mang nhu hòa, chiếu lên thân thể của thiếu niên kia, mà sau khi ánh sáng chiếu xuống thì huyết khí quỷ dị trên mặt của thiếu niên cũng bắt đầu dừng lại từ từ.\n" +
            "\n" +
            "Huyết khi giằng co chừng một nén nhang, cuối cũng cũng bắt đầu rút lui dần, sau cùng rút về lòng bàn tay của thiếu niên.\n" +
            "\n" +
            "Lão giả tóc trắng nhìn thấy một màn này thì lập tức thở phào nhẹ nhõm như trút được gánh nặng, sau đó xoay người lại, khom người nói với nam tử trung niên và mỹ phụ mặc cung trang đang lo lắng đứng chờ ở bên cạnh:\n" +
            "\n" +
            "- Chúc mừng vương thượng, vương hậu, rốt cuộc điện hạ cũng đã vượt qua được cửa ải ba năm này, ba năm tiếp theo hẳn là sẽ không có việc gì.\n" +
            "\n" +
            "Nam tử trung niên và mỹ phụ mặc cung trang kia nghe thấy thế thì trên mặt đều lộ ra vẻ vui mừng, hai nắm tay siết chặt cũng dần buông lỏng.\n" +
            "\n" +
            "- Tần Sư, Nguyên Nhi năm nay cũng đã mười ba tuổi, những thiếu niên bình thường ở tuổi này đều đã hình thành bát mạch, có thể bắt đầu tu luyện, vậy Nguyên Nhi thì sao?\n" +
            "\n" +
            "Nam tử uy nghiêm thân mặc hoàng bào kia nhìn về phía lão giả tóc trắng hỏi dò với vẻ chờ mong.\n" +
            "\n" +
            "Nghe thấy hắn hỏi vậy, vẻ mặt của lão giả tóc trắng lập tức lộ ra vẻ ủ rũ, hắn khẽ lắc đầu, nói:\n" +
            "\n" +
            "- Vương thượng, lần này lão phu vẫn không thăm dò được bát mạch bên trong cơ thể của điện hạ…\n" +
            "\n" +
            "Nam tử uy nghiêm nghe thấy thế thì trong ánh mắt cũng dần tối lại.\n" +
            "\n" +
            "ở trong thiên địa này, tu hành chi đạo, bắt đầu từ trong cơ thể con người, trong cơ thể con người có vô số kinh mạch, mà thứ quan trọng nhất trong đó chính là bát đại mạch, ngoại trừ một số tình huống đặc biệt ra thì trong cơ thể của người bình thường sẽ dần dần hình thành bát mạch ở độ tuổi chừng mười hai mười ba gì đó, lúc này, cần phải tìm ra bát mạch trong có thể, chỉ khi nào tìm ra được bát mạch trong cơ thể thì mới có thể bắt đầu tu luyện, thôn nạp nguyên lực đất trời, đả thông bát mạch.\n" +
            "\n" +
            "Đây là khai mạch cảnh, điểm bắt đầu của tu luyện.\n" +
            "\n" +
            "Còn tu luyện giả thì nhờ vào việc hấp thụ bản nguyên chi lực của thiên địa, cho nên tự động thoát thai hoán cốt, cũng được gọi là nguyên sư.\n" +
            "\n" +
            "Tần Sư nhìn thấy vẻ mặt ảm đạm của nam tử trung niên, trong lòng cũng có phần không nỡ, thở dài một tiếng, nói:\n" +
            "\n" +
            "- Điện hạ vốn là thánh long chi mệnh, vốn sẽ kinh diễm nhân thế, ngạo thị thương khung, sao lại gặp phải kiếp nạn này…\n" +
            "\n" +
            "Nam tử trung niên siết chặt nắm tay, vành mắt của mỹ phụ mặc cung trang đứng bên cạnh cũng đỏ hoe, sau đó che miệng ho khan vài tiếng.\n" +
            "\n" +
            "- Xin vương hậu bảo trọng thân thể, lúc trước ngài đã tổn thất rất nhiều tinh huyết để tẩm bổ cho điện hạ, không thể để tâm tình bị kích động.\n" +
            "\n" +
            "Tần Sư thấy thế thì vội vàng lên tiếng khuyên giải.\n" +
            "\n" +
            "Mỹ phụ mặc cung trang kia chỉ khoát tay, ánh mắt buồn bã nhìn về phía thiếu niên đang ngồi xếp bằng trên giường, nói:\n" +
            "\n" +
            "- Độc trong cơ thể Nguyên Nhi, ba năm sẽ bộc phát một lần, lần sau lại càng lợi hại hơn lần trước, nếu muốn trừ bỏ tận gốc thì chỉ có thể dựa vào chính bản thân nó, nhưng bây giờ bát mạch của nó không xuất hiện, ba năm sau phải làm sao đây?\n" +
            "\n" +
            "Tần Sư im lặng hồi lâu mới chậm rãi đáp lời:\n" +
            "\n" +
            "- Ba năm sau, sẽ không thể dùng ngoại lực để áp chế nữa, nếu như vẫn cứ như thế thì chỉ sợ tính mạng của điện hạ sẽ gặp nguy hiểm.','https://res.cloudinary.com/dgzx10tjl/image/upload/zyrfhhhrgklzwfdx4jfp.jpg',0)";
    private String SQLQuery008 = "INSERT INTO truyen VALUES (null,'ĐẠI CHÚA TỂ','Đại thiên thế giới, nơi các vị diện giao nhau, vạn tộc hiện lên như nấm, quần hùng tụ hội, một vị Thiên chi chí tôn đến từ hạ vị diện tại vô tận thế giới diễn lại một truyền kỳ mà mọi người hướng tới, theo đuổi con đường Chúa tể.\n" +
            "\n" +
            "Hỏa vực vô tận, Viêm Đế nắm giữ, vạn hỏa đốt trời xanh.\n" +
            "\n" +
            "Trong võ cảnh, Võ Tổ chi uy, chấn nhiếp càn khôn. Đọc Truyện Kiếm Hiệp Hay Nhất: http://truyenfull.vn\n" +
            "\n" +
            "Bắc Hoang khâu, Vạn Mộ địa, Bất Tử chi chủ trấn thiên địa\n" +
            "\n" +
            "Tây Thiên điện, Bách Chiến chi hoàng, chiến uy không thể địch.\n" +
            "\n" +
            "...\n" +
            "\n" +
            "Thiếu niên bước ra từ Bắc Linh cảnh, cưỡi Cửu U Minh Tước xông đến thế giới hỗn loạn đầy đặc sắc, con đường Chúa tể, ai nổi ai chìm?\n" +
            "\n" +
            "Đại thiên thế giới, vạn đạo tranh phong, ta làm đại chúa tể.\n" +
            "\n" +
            "Nắng chói chang, ánh mặt trời rực lửa dần lên cao khiến mặt đất bên dưới gần như khô cằn, dương liễu rủ xuống, cành lá cũng như co lại, đìu hiu không sức sống.\n" +
            "\n" +
            "Trong một khu đất trống sáng sủa được hàng liễu bao quanh có mấy trăm người đang ngồi lặng yên, họ đều chỉ là những thiếu niên, thiếu nữ mới lớn. Lúc này, bọn họ đều mang vẻ mặt nghiêm túc, hai mắt khép hờ, hơi thở đều đặn rất có tiết tấu. Mỗi lần hít thở, quanh người bọn họ dường như xuất hiện những điểm sáng rất nhỏ mà mắt thường khó có thể nhìn thấy rõ.\n" +
            "\n" +
            "Cơn gió thoảng qua khiến y phục của bọn họ phất phơ tạo nên vẻ đẹp lạ thường.\n" +
            "\n" +
            "Trước mặt mấy trăm người bọn họ là một bệ đá. Trên bệ đá, tương tự cũng có một người đang ngồi lặng yên. Hắn chắp tay trước ngực, mắt nhắm chặt như đang ở trong trạng thái tu luyện nào đấy.\n" +
            "\n" +
            "Người này là một thiếu niên có mái tóc màu đen mềm mại nhưng hơi rối, gương mặt gầy gò còn chút non nớt khiến người ta thoạt nhìn có cảm giác khá dễ chịu.\n" +
            "\n" +
            "Hiện tại, quanh người thiếu niên này hiện ra một tầng hào quang mà mắt thường cũng có thể thấy được. Trong tầng hào quang này, dường như có một luồng năng lượng kỳ lạ đang mạnh mẽ tiến vào trong cơ thể hắn.\n" +
            "\n" +
            "Dưới bệ đá, một số thiếu niên đột nhiên mở to tắt nhìn tầng hào quang quanh người thiếu niên trên bệ đá kia, nhịn không được phải liếm môi, trên mặt lộ lên chút hâm mộ lẫn khâm phục rồi sau đó tiếng bàn luận ồn ào bắt đầu phá đi vẻ yên tĩnh ban nãy.\n" +
            "\n" +
            "\"Mục ca thật lợi hại, chúng ta còn đang cảm ứng thiên địa linh khí mà huynh ấy đã thành công đạt tới Linh Động cảnh rồi, thật không hổ là người đứng đầu của Đông viện địa giới chúng ta.\"\n" +
            "\n" +
            "\"Ha ha, đương nhiên rồi, không chỉ Đông viện chúng ta mà ngay cả Bắc Linh viện này, chỉ sợ không có mấy người ngang tuổi có thể sánh được với Mục ca.\"\n" +
            "\n" +
            "Một thiếu niên áo xám ngồi đằng trước dường như quen biết người đang ngồi trên bục đá kia nên khi nghe thấy những lời bàn tán này thì không khỏi mỉm cười đắc ý, nói khẽ: \"Trong cả Bắc Linh cảnh này, chỉ có mình Mục ca được chọn tham gia Linh lộ. Các đệ có biết những người được tham gia Linh lộ biến thái tới cỡ nào không? Năm đó, cả Bắc Linh cảnh chúng ta cũng vì việc này mà nháo nhào một hồi đấy. Người từ nơi đó đi ra đều sẽ được Ngũ đại viện nhận lấy.\"\n" +
            "\n" +
            "\"Ngũ đại viện?\" Không ít thiếu niên nghe thấy cái tên cực kì nổi danh này đều không nhịn được mà nuốt một ngụm nước miếng, trong mắt tràn đầy thèm muốn. Nơi đó là ước mơ, là khát vọng mà mọi thiếu niên luôn hướng tới. Nhưng Ngũ đại viện lại tuyển chọn hết sức nghiêm khắc nên những người có thể tiến vào trong không ai không phải là thiên tài. Chỉ cần tiến vào trong đó thì tương lai của kẻ đó sẽ vô cùng rộng mở.\n" +
            "\n" +
            "\"Tuy rằng Mục ca rất lợi hại nhưng ta nghe nói, huynh ấy là người đầu tiên bị đuổi khỏi Linh lộ khi chưa tới thời gian kết thúc, hình như huynh ấy chỉ ở đó một năm thôi.\"\n" +
            "\n" +
            "Một thiếu niên khác do dự một lúc rồi lên tiếng, sau đó nhanh chóng bổ sung: \"Chúng ta đều biết năng lực của Mục ca, huynh ấy không thể kém hơn những thiên thiên tài yêu nghiệt đến từ khắp nơi trên Đại thiên thế giới trong Linh lộ được, huynh ấy bị đuổi chắc là do bị đối xử không công bằng thôi.\"\n" +
            "\n" +
            "Đám thiếu niên thiếu nữ quay mặt nhìn nhau, tại Bắc Linh viện, thậm chí cả Bắc Linh cảnh thì chuyện này cũng không tính là bí mật gì. Bọn họ cảm thấy tiếc nuối, đồng thời cũng tò mò muốn biết rốt cuộc do đâu mà một người xuất sắc khiến đám người kiêu ngạo bọn họ cũng phải tin phục như Mục ca lại bị Linh lộ đuổi ra.','https://static.8cache.com/cover/o/eJzLyTDR1_Xy0S3MMy4rM07J1w9z8nUxdk1Jd3f31HeEgmzvcn33jMrwfH_TyiyLSP1iAwDUoBDp/dai-chua-te.jpg',0)";
    private String SQLQuery009 = "INSERT INTO truyen VALUES (null,'VĨNH SINH','Vương triều Đại Ly, Long Uyên tỉnh, đại thế gia số một tại đây là Phương gia, trong nội phủ \"Vạn Tượng Viên\", sáng sớm sương mù còn chưa tan, một đám đệ tử đã bắt đầu luyện tập võ công.\n" +
            "\n" +
            "Gia chủ Phương gia chính là tổng đốc tỉnh Long Uyên, tổ tiên Phương gia trăm năm trước chính là người của Vũ Hóa Môn, một trong thập đại môn phái tiên đạo, sau đó theo thái tổ Đại Ly hoàng triều Vũ Hoàng Đế bình định thiên hạ.\n" +
            "\n" +
            "Tỉnh Long Uyên là nơi hang rồng vực cọp vốn là cố đô tiền triều. Khi vương triều Đại Ly kiến quốc muốn đặt đế đô tại đây, nhưng vì thủy khí nơi này quá nặng, cuối cùng đành bỏ ý định đó.\n" +
            "\n" +
            "Vương triều Đại Ly chính là lấy lửa làm căn cơ để thiên hạ thái bình, thuộc tính hỏa. Lại bị thủy áp chế, không thích hợp đóng đô tại Long Uyên, cho nên mới quyết định đóng đô tại phía nam, có tên là \"Ly Kinh\".\n" +
            "\n" +
            "Thế nhưng triều đình vẫn đem cố đô tiền triều giao cho Phương gia nắm giữ, có thể thấy được triều đình tín nhiệm đối với Phương gia như thế nào.\n" +
            "\n" +
            "Chính bởi vì như vậy, gia tộc Phương gia mới hiển hách như ngày nay, phủ đệ rộng nghìn mẫu. Đình viện bên trong san sát, hoa viên nhiều vô kể, người bình thường khi tiến vào nhất định sẽ bị lạc đường không tìm được lối ra.\n" +
            "\n" +
            "Từ cái tên nội phủ Phương gia hoa viên có thể thấy được, \"Vạn Tượng Viên\" Đã bao hàm toàn diện ý nghĩa trong đó.\n" +
            "\n" +
            "Phành phành phành phành.\n" +
            "\n" +
            "Quyền phong gào thét, không khí chấn động.\n" +
            "\n" +
            "Những đệ tử này động tác mạnh mẽ có lực như ác hổ vồ dê, như sư tử bắt trâu, như chim ưng cắp bạch thỏ, như hạc đứng trên ngọn tùng... Động tác toàn thân phối hợp với eo lưng nhanh nhẹn sắc bén.\n" +
            "\n" +
            "Nhất là tứ chi cùng với cột sống kết hợp nhuần nhuyễn động tác thu vào bung ra tùy ý, giống như cây cung kéo căng, quyền cước như mũi tên, kéo lên là tự phóng, rõ ràng căn cơ bọn họ đã rất vững chắc.\n" +
            "\n" +
            "Chỉ có điều lúc này một cặp mắt đang ở phía xa trong bóng tối, phía sau ngọn giả sơn len lén nhìn động tác của bọn họ.\n" +
            "\n" +
            "Dĩ nhiên là muốn học trộm võ công.\n" +
            "\n" +
            "Chủ nhân của ánh mắt là một thiếu niên mười lăm mười sáu tuổi, áo màu xám đội trên đầu một cái mũ quả dưa, đó chỉ là một gã sai vặt.\n" +
            "\n" +
            "\"Một tháng trước ta phát hiện một cống thoát nước có thể nối thẳng tới đám nước bùn dưới hòn giả sơn trong nội viện. Nếu không phải như vậy, ta cũng không có khả năng tiến vào nội viện để học trộm võ công. Nếu như bị phát hiện, chỉ có con đường chết... Thế nhưng nếu không làm vậy Hàn tổng sẽ bắt ta làm nô tài cả đời mất\".\n" +
            "\n" +
            "Phương Hàn cẩn thận quan sát những động tác của đám đệ tử Phương gia, bất luận động tác gì, hắn đều ghi tạc trong đầu.\n" +
            "\n" +
            "Không sai, đúng là hắn đang học trộm võ công.\n" +
            "\n" +
            "Phương Hàn là một gã tiểu nô tài trong phủ Phương gia.\n" +
            "\n" +
            "Phụ thân hắn vốn cũng là gia đinh của Phương gia, cho nên khi hắn vừa sinh ra, đã định trước là nô bộc, thậm chí con cháu mấy đời đều phải dốc sức vì Phương gia.\n" +
            "\n" +
            "Một thân phận gia đinh hèn mọn lại dám lén lút tiến nhập nội phủ học trộm võ đạo chân truyền của chủ nhân, loại hành vi này có thể nói là không sợ chết cả gan làm loạn.\n" +
            "\n" +
            "\"Tới đây đi!\"\n" +
            "\n" +
            "Trong ánh mắt Phương Hàn hiện lên một trung niên mặc tử sắc đại bào xuất hiện ở nơi xa xa luyện võ trường, thân thể hắn không khỏi đông cứng.\n" +
            "\n" +
            "Trung niên tử bào này, thân cao chín thước, ngang tàng sừng sững, lưng đứng thẳng như kiếm như thương, giống như một tòa núi cao mang theo hơi thở áp bức, tuy rằng cách xa nhưng Phương Hàn có một loại cảm giác hô hấp muốn đình chỉ.\n" +
            "\n" +
            "Đây là vị cao thủ nhất đẳng của Phương gia \"Cự Linh Thủ\" Phương Đồng! Uy danh hiển hách, danh chấn khắp hai vùng nam bắc, tới đây để truyền thụ võ nghệ cho đám đệ tử Phương gia.\n" +
            "\n" +
            "\"Làm quan! Có cửu phẩm mười tám cấp! Nhất phẩm thừa tướng, cho đến cửu phẩm tuần kiểm địa phương! Mà chúng ta tu luyện thân thể, cũng chia làm thập trọng! Nhất dưỡng sinh, nhị trọng luyện lực, tam trọng chiêu thức, tứ trọng cương nhu, ngũ trọng thần lực, lục trọng khí tức, thất trọng nội tráng, bát trọng thần dũng, cửu trọng thông linh! Thập trọng thần biến!\"\n" +
            "\n" +
            "Phương Đồng \"Cự Linh Thủ\" Vừa xuất hiện nhất thời toàn bộ đệ tử trong luyện võ trường đều đình chỉ động tác, đứng nghiêm chỉnh nghe hắn giáo huấn.\n" +
            "\n" +
            "Phương Hàn cũng vểnh tai tỉ mỉ lắng nghe.','https://img.dtruyen.com/public/images/large/vinhsinhVdxZCwH1FA.jpg',0)";
    private String SQLQuery0010 = "INSERT INTO truyen VALUES (null,'PHI THIÊN','- Đừng chạy! Họ Miêu kia, ngươi không chạy thoát được đâu, đứng lại cho lão tử!\n" +
            "\n" +
            "Ba thiếu niên tay cầm trường đao đang chạy rất nhanh trong dãy núi đen kịt vô cùng kỳ quái, thỉnh thoảng quơ đao đe dọa bắt người đang chạy trốn trước mặt dừng lại.\n" +
            "\n" +
            "Đe dọa vô dụng, chẳng những người trước mặt không ngừng, ngược lại còn chạy nhanh hơn.\n" +
            "\n" +
            "Thiếu niên chạy trước tay cầm một thanh đao giết heo không thèm nghe, vừa chạy vừa quay đầu lại rống lên một tiếng:\n" +
            "\n" +
            "- Chó điên, sao không xem thử đây là địa phương nào, chẳng lẽ là đầu óc các ngươi có bệnh rồi sao?!\n" +
            "\n" +
            "Hắn chịu dừng lại mới lạ, dừng lại sẽ lập tức mất mạng, nên vẫn tiếp tục chạy như điên. Dưới chân hắn vang lên những tiếng rắc rắc không ngừng, đạp lên chỗ nào cỏ màu đen chỗ ấy lập tức tan tác thành bụi đen.\n" +
            "\n" +
            "Bốn phía xung quanh, cỏ là màu đen, cây cối cũng là màu đen, tất cả thực vật đều là màu đen.\n" +
            "\n" +
            "Không phải là bị nhuộm thành màu đen, cũng không phải trời sinh màu đen, mà là toàn bộ bị hóa thành than đen, mười vạn năm trước là thứ gì, mười vạn năm sau vẫn y như vậy. Tựa hồ thời gian đã dừng lại ở chỗ này, hết thảy thực vật trông như những pho tượng đá màu đen vô cùng sinh động, chìm trong màn sương trắng mịt mờ.\n" +
            "\n" +
            "Thế giới giống như u minh này có tên là Vạn Trượng Hồng Trần, lời đồn mười vạn năm trước có mười vạn thiên binh thiên tướng vượt qua tinh không mênh mông đuổi giết một đại ma đầu đến đây. Không ngờ rằng đại ma đầu kia quá mức lợi hại, mười vạn thiên binh thiên tướng bèn bày ra đại trận tuyệt sát này, đồng quy ư tận với đại ma đầu kia ở chỗ này.\n" +
            "\n" +
            "Mười vạn năm qua, tuyệt đại đa số thời điểm màn sương trước mắt đều đỏ như màu máu quỷ quyệt kinh khủng, huyết vụ đáng sợ tựa hồ có thể cắn nuốt hết thảy. Bất kể là người hay là quỷ, hoặc là thần cũng không dám tự tiện xông vào nơi đây một bước, làm cho hết thảy sinh linh dừng bước.\n" +
            "\n" +
            "Bất quá cách mỗi một ngàn năm, tòa đại trận tuyệt sát này sẽ mở một góc lưới, khi huyết vụ biến thành sương trắng, phàm nhân phổ thông có thể tiến vào thăm dò. Nhưng yêu ma quỷ quái và các loại sinh linh khác vẫn không có cách nào tiến vào một bước, nếu không tất bị sương mù này quỷ dị hóa thành một bãi nước đen. Dường như tu sĩ hùng mạnh tới mức nào cũng không có cách nào chống lại sương mù này ăn mòn, cực kỳ quỷ dị, không ai có thể hiểu được nguyên nhân.\n" +
            "\n" +
            "Nhưng nơi này là chỗ táng thân cuối cùng của tiên ma, có thể tưởng tượng được đồ vật tiên ma mang theo tùy thân cũng cùng bị mai táng nơi này với tiên ma, khiến cho không biết bao nhiêu kẻ tu hành mơ ước. Đồng thời nơi này còn sản sinh một loại thảo dược được đặt tên là tiên thảo Tinh Hoa, là chí bảo thánh dược chữa thương trong giới tu hành.\n" +
            "\n" +
            "Mỗi khi thời khắc ngàn năm Vạn Trượng Hồng Trần mở ra một lần lại tới, chính là lúc những kẻ tu hành rục rịch muốn động. Thế nhưng bọn họ lại không có cách nào đi vào, vì vậy dùng lợi ích dụ dỗ người phàm đi vào thu nhặt. Bất kể là ai, chỉ cần có thể tìm được di vật tiên ma và tiên thảo Tinh Hoa, sẽ được thu vào tiên môn vô điều kiện.\n" +
            "\n" +
            "Nhưng nơi này còn có một loại quái vật, truyền thuyết là quái vật giữ lăng cho tiên ma, thích máu, giết người như ngóe.\n" +
            "\n" +
            "Cho nên nếu không phải là người lâm vào tuyệt lộ hoặc là bất cần mạng sống, sẽ không ai chịu chạy vào nơi này mạo hiểm, muốn thành tiên cũng phải có mạng để hưởng thụ.\n" +
            "\n" +
            "Miêu Nghị không phải là người lâm vào tuyệt lộ hoặc là bất cần mạng sống, hắn chỉ mới mười bảy tuổi, tuy không lớn lắm nhưng cũng không nhỏ nữa, thiếu niên ở vào tuổi của hắn lấy vợ sinh con là chuyện rất bình thường.\n" +
            "\n" +
            "Hắn chọn trúng nữ nhi lão Lý tiệm đậu hủ ở đối diện nhà mình, tìm bà mai đi cầu hôn, sau khi lão Lý tiệm đậu hủ biết chuyện này lập tức đánh đuổi bà mai ra khỏi cửa. Hai nhà đối diện nhau chỉ cách một con đường, biết nhau rất rõ ràng, một tiểu tử giết heo không tiền của không địa vị, còn có gánh nặng hai đứa em phải nuôi, làm sao có thể cưới nữ nhi của mình?!\n" +
            "\n" +
            "Bà mai có thể nói cho người chết sống lại cũng vô ích, vợ lão Lý thóa mạ hết nửa ngày, toàn là lời lẽ khó nghe đại loại như cóc ghẻ mà đòi ăn thịt thiên nga…\n" +
            "\n" +
            "Không cầu thân còn đỡ, vừa nhắc tới chuyện này nhà lão Lý lập tức phòng Miêu Nghị giống như đề phòng cướp, không để cho nữ nhi từ nhỏ đã chơi thân với Miêu Nghị gặp mặt hắn, sợ bị Miêu Nghị bắt đi. Hai nhà coi như hoàn toàn cắt đứt lui tới, hàng xóm láng giềng với nhau trở mặt cực nhanh.\n" +
            "\n" +
            "Miêu Nghị cũng không thích nữ nhi nhà lão Lý lắm, chỉ vì gia cảnh nên không có lòng dạ nói chuyện yêu đương, chẳng qua là theo phong tục địa phương mà thôi. Không thành thì thôi hắn cũng không coi trọng mấy, bất quá chuyện này làm cho hắn hiểu ra một đạo lý.\n" +
            "\n" +
            "Dưỡng phụ mẫu đã sớm qua đời, khi còn sống đối xử với Miêu Nghị rất tốt, để lại một trai một gái. Miêu Nghị không muốn để cho đệ đệ muội muội bước theo vết xe đổ của mình, vừa đúng lúc Vạn Trượng Hồng Trần mở ra, hắn định liều mạng một phen kiếm chút tiền đồ cho đệ đệ muội muội.','https://readslove.com/story/cover/5c8a95/pt.jpg',0)";
    private String SQLQuery0011 = "INSERT INTO truyen VALUES (null,' KIẾM ĐẠO ĐỘC TÔN','Trong một gian phòng u ám, người thiếu niên lười biếng nằm trên giường, miệng thì thầm:\n" +
            "\n" +
            "- Không ngờ chủ nhân của thân thể này cũng tên Diệp Trần, tướng mạo có ba bốn phần giống ta, không lẽ là đặc biệt chuẩn bị cho ta?\n" +
            "\n" +
            "Kiếp trước Diệp Trần là sinh viên hệ Vật Lý của một trường đại học danh tiếng, thông minh và có khả năng quan sát nhạy bén, bởi vì tai nạn ngoài ý muốn trong phòng thực nghiệm, tất cả máy móc nổ tung nên linh hồn hắn mới bị đưa đến thế giới này.\n" +
            "\n" +
            "Nếu như là người thường đột nhiên bị chuyển đến một môi trường xa lạ, linh hồn tiến nhập vào một thân thể xa lạ, nhất định sẽ phát điên, nhưng Diệp Trần không có cảm giác gì, chỉ là có chút kinh ngạc mà thôi.\n" +
            "\n" +
            "Nghĩ đến đây, Diệp Trần hoài nghi không biết có phải mình biến thái hay không, một chuyện lớn như vậy mà không có cảm giác gì, vậy thì còn cảm giác với cái gì nữa!\n" +
            "\n" +
            "Kí ức tạm thời dung hợp, Diệp Trần đã tiêu hóa hết mười mấy năm kí ức của thân thể này, trong kí ức, hắn là đệ tử của đại gia tộc một phương nào đó, mười hai tuổi bái nhập Lưu Vân Tông, mười bốn tuổi chính thức trở thành đệ tử ngoại môn, coi như khổ tận cam lai.\n" +
            "\n" +
            "Điều duy nhất đáng lên án là hai năm từ mười hai tuổi đến mười bốn tuổi, nguyên chủ nhân trừ việc tu luyện nội khí lên Luyện Khí Cảnh tầng thứ tư ra, những phương diện khác hầu như không có tiến bộ, một bộ Hành Vân Kiếm Pháp cơ sở cũng mới chỉ luyện đến chiêu thứ ba. Bạn đang đọc truyện được lấy tại chấm cơm.\n" +
            "\n" +
            "- Xem ra muốn ở lại thế giới này, việc đầu tiên là phải xóa bỏ biệt hiệu ngốc nghếch.\n" +
            "\n" +
            "Diệp Trần rất bất mãn với việc cố chủ nhân của thân thể này bị gọi là kẻ ngốc, dù sao bây giờ họ cũng là một thể, một người không hề ngốc nghếch thế nào cũng không muốn đeo theo cái tên nhục nhã đó.\n" +
            "\n" +
            "Hạ quyết tâm, Diệp Trần chớp mắt, hồi tưởng lại những việc xảy ra trước năm mười hai tuổi.\n" +
            "\n" +
            "Lưu Vân Tông nằm ở Thiên Phong Quốc Bắc Bộ, cùng bốn môn phái khác trong nước xưng thành \"Nhất Cốc Nhất Trang Tam Tông\", trừ năm thế lực mạnh nhất, bát đại gia tộc nắm chắc mệnh mạch võ lâm của Thiên Phong Quốc, ngay cả vương đình cũng phải dựa vào, cố gắng không động chạm đến.\n" +
            "\n" +
            "Diệp Trần là người của Diệp gia, một trong bát đại gia tộc.\n" +
            "\n" +
            "Diệp gia phát triển từ hơn hai trăm năm trước, lấy lá trà và tơ lụa làm mặt hàng kinh doanh chính, tích lũy tài sản khổng lồ, tục ngữ có câu, có nhiều tài phú mà không đủ thực lực bảo vệ thì chẳng khác gì một miếng thịt mỡ không được bố trí phòng ngự, ai cũng muốn đến cắn một miếng.\n" +
            "\n" +
            "Trải qua vô vàn trắc trở và gian trá, Diệp gia gia chủ đương thời đúc rút kinh nghiệm xương máu, quyết định lợi dụng tài lực hùng hậu phát triển gia tộc thành võ lâm thế gia, sánh vai cùng các thế gia khác, cho nên mới có Diệp gia một trong bát đại gia tộc hôm nay.\n" +
            "\n" +
            "Là đệ tử Diệp gia, đi đến đâu mắt cũng cao hơn đỉnh đầu, người ngoài lắp bắp còn chẳng kịp, nhưng Diệp Trần thì không nằm trong số đó, nguyên nhân là vì thiên phú tu luyện của hắn thực sự quá kém, khiến người ta vô cùng thất vọng.\n" +
            "\n" +
            "May mà phụ thân hắn là Diệp gia đương đại gia chủ, mẫu thân hắn là trưởng lão ngoại môn của một trong ba tông Nam La Tông, người ngoài không ai dám công khai chế giễu hay coi thường hắn.\n" +
            "\n" +
            "Mặc dù vậy, Diệp Trần vẫn bị bắt nạt như thường.\n" +
            "\n" +
            "Kí ức sâu đậm nhất là con trai lớn và con trai thứ hai của đại bá cùng con trai tam thúc thường xuyên tìm hắn gây chuyện, thậm chí tìm chỗ không có người sỉ nhục hắn, ngay cả thị nữ Tiểu Cúc của hắn cũng bị những thị nữ khác coi thường, mỗi lần đều chạy đến tìm hắn khóc lóc, kể lể oan ức.\n" +
            "\n" +
            "- Hừ, thù này nhất định phải trả.\n" +
            "\n" +
            "Diệp Trần thần tình hung ác, siết chặt nắm tay.\n" +
            "\n" +
            "Hắn giận như vậy không phải không có đạo lý, kiếp này hắn là hai Diệp Trần dung hợp thể, nên có kí ức tâm tình kiếp trước, cũng có kí ức tâm tình kiếp này, nói cách khác, hai Diệp Trần đều là hắn, tuy hai mà một, không hề phân biệt.\n" +
            "\n" +
            "Bất luận là Diệp Trần nào bị sỉ nhục, đều khiến hắn nổi giận.\n" +
            "\n" +
            "Ra sức khống chế cảm xúc, Diệp Trần tiếp tục hồi tưởng.','https://nae.vn/ttv/ttv/public/images/story/33c8c55384b6105ec563cd89aa9c4a60b0c1a889899d11457dc26cd637288af5.jpg',0)";
    private String SQLQuery0012 = "INSERT INTO truyen VALUES (null,'DỊ THẾ TÀ QUÂN','Trong một gian phòng u ám, người thiếu niên lười biếng nằm trên giường, miệng thì thầm:\n" +
            "\n" +
            "- Không ngờ chủ nhân của thân thể này cũng tên Diệp Trần, tướng mạo có ba bốn phần giống ta, không lẽ là đặc biệt chuẩn bị cho ta?\n" +
            "\n" +
            "Kiếp trước Diệp Trần là sinh viên hệ Vật Lý của một trường đại học danh tiếng, thông minh và có khả năng quan sát nhạy bén, bởi vì tai nạn ngoài ý muốn trong phòng thực nghiệm, tất cả máy móc nổ tung nên linh hồn hắn mới bị đưa đến thế giới này.\n" +
            "\n" +
            "Nếu như là người thường đột nhiên bị chuyển đến một môi trường xa lạ, linh hồn tiến nhập vào một thân thể xa lạ, nhất định sẽ phát điên, nhưng Diệp Trần không có cảm giác gì, chỉ là có chút kinh ngạc mà thôi.\n" +
            "\n" +
            "Nghĩ đến đây, Diệp Trần hoài nghi không biết có phải mình biến thái hay không, một chuyện lớn như vậy mà không có cảm giác gì, vậy thì còn cảm giác với cái gì nữa!\n" +
            "\n" +
            "Kí ức tạm thời dung hợp, Diệp Trần đã tiêu hóa hết mười mấy năm kí ức của thân thể này, trong kí ức, hắn là đệ tử của đại gia tộc một phương nào đó, mười hai tuổi bái nhập Lưu Vân Tông, mười bốn tuổi chính thức trở thành đệ tử ngoại môn, coi như khổ tận cam lai.\n" +
            "\n" +
            "Điều duy nhất đáng lên án là hai năm từ mười hai tuổi đến mười bốn tuổi, nguyên chủ nhân trừ việc tu luyện nội khí lên Luyện Khí Cảnh tầng thứ tư ra, những phương diện khác hầu như không có tiến bộ, một bộ Hành Vân Kiếm Pháp cơ sở cũng mới chỉ luyện đến chiêu thứ ba. Bạn đang đọc truyện được lấy tại chấm cơm.\n" +
            "\n" +
            "- Xem ra muốn ở lại thế giới này, việc đầu tiên là phải xóa bỏ biệt hiệu ngốc nghếch.\n" +
            "\n" +
            "Diệp Trần rất bất mãn với việc cố chủ nhân của thân thể này bị gọi là kẻ ngốc, dù sao bây giờ họ cũng là một thể, một người không hề ngốc nghếch thế nào cũng không muốn đeo theo cái tên nhục nhã đó.\n" +
            "\n" +
            "Hạ quyết tâm, Diệp Trần chớp mắt, hồi tưởng lại những việc xảy ra trước năm mười hai tuổi.\n" +
            "\n" +
            "Lưu Vân Tông nằm ở Thiên Phong Quốc Bắc Bộ, cùng bốn môn phái khác trong nước xưng thành \"Nhất Cốc Nhất Trang Tam Tông\", trừ năm thế lực mạnh nhất, bát đại gia tộc nắm chắc mệnh mạch võ lâm của Thiên Phong Quốc, ngay cả vương đình cũng phải dựa vào, cố gắng không động chạm đến.\n" +
            "\n" +
            "Diệp Trần là người của Diệp gia, một trong bát đại gia tộc.\n" +
            "\n" +
            "Diệp gia phát triển từ hơn hai trăm năm trước, lấy lá trà và tơ lụa làm mặt hàng kinh doanh chính, tích lũy tài sản khổng lồ, tục ngữ có câu, có nhiều tài phú mà không đủ thực lực bảo vệ thì chẳng khác gì một miếng thịt mỡ không được bố trí phòng ngự, ai cũng muốn đến cắn một miếng.\n" +
            "\n" +
            "Trải qua vô vàn trắc trở và gian trá, Diệp gia gia chủ đương thời đúc rút kinh nghiệm xương máu, quyết định lợi dụng tài lực hùng hậu phát triển gia tộc thành võ lâm thế gia, sánh vai cùng các thế gia khác, cho nên mới có Diệp gia một trong bát đại gia tộc hôm nay.\n" +
            "\n" +
            "Là đệ tử Diệp gia, đi đến đâu mắt cũng cao hơn đỉnh đầu, người ngoài lắp bắp còn chẳng kịp, nhưng Diệp Trần thì không nằm trong số đó, nguyên nhân là vì thiên phú tu luyện của hắn thực sự quá kém, khiến người ta vô cùng thất vọng.\n" +
            "\n" +
            "May mà phụ thân hắn là Diệp gia đương đại gia chủ, mẫu thân hắn là trưởng lão ngoại môn của một trong ba tông Nam La Tông, người ngoài không ai dám công khai chế giễu hay coi thường hắn.\n" +
            "\n" +
            "Mặc dù vậy, Diệp Trần vẫn bị bắt nạt như thường.\n" +
            "\n" +
            "Kí ức sâu đậm nhất là con trai lớn và con trai thứ hai của đại bá cùng con trai tam thúc thường xuyên tìm hắn gây chuyện, thậm chí tìm chỗ không có người sỉ nhục hắn, ngay cả thị nữ Tiểu Cúc của hắn cũng bị những thị nữ khác coi thường, mỗi lần đều chạy đến tìm hắn khóc lóc, kể lể oan ức.\n" +
            "\n" +
            "- Hừ, thù này nhất định phải trả.\n" +
            "\n" +
            "Diệp Trần thần tình hung ác, siết chặt nắm tay.\n" +
            "\n" +
            "Hắn giận như vậy không phải không có đạo lý, kiếp này hắn là hai Diệp Trần dung hợp thể, nên có kí ức tâm tình kiếp trước, cũng có kí ức tâm tình kiếp này, nói cách khác, hai Diệp Trần đều là hắn, tuy hai mà một, không hề phân biệt.\n" +
            "\n" +
            "Bất luận là Diệp Trần nào bị sỉ nhục, đều khiến hắn nổi giận.\n" +
            "\n" +
            "Ra sức khống chế cảm xúc, Diệp Trần tiếp tục hồi tưởng.','https://zingaudio.net/wp-content/uploads/2020/10/di-the-ta-quan.jpg',0)";
    private String SQLQuery0014 = "INSERT INTO truyen VALUES (null,'NHÂN ĐẠO CHÍ TÔN','Trời còn chưa tỏ, mười vạn dặm Đại Hoang chìm trong u tối. Những tia sáng đầu tiên bắt đầu chiếu lên dãy Kiếm Sơn soi tỏ làn mây phiêu đãng lượn lờ quanh sườn núi. Nơi chúng vươn tới trước nhất là Kim Đỉnh trên đỉnh núi.\n" +
            "\n" +
            "Đại điện Kim Đỉnh làm từ lưu ly tỏa ra ánh sáng rực rỡ làm rạng ngời cả biển mây, quả thực vô cùng chói mắt.\n" +
            "\n" +
            "Giữa biển mây dập dờn bao quanh thân núi có vô số đại điện to lớn mang vẻ cổ xưa như đang bồng bềnh phát ra vẻ thần thánh mà trang nghiêm.\n" +
            "\n" +
            "Đó chính là thánh địa chung của ba nghìn bộ lạc nhân tộc trong mười vạn dặm Đại Hoang, Kiếm Môn!\n" +
            "\n" +
            "Luyện Khí Sĩ của Kiếm Môn tầng tầng lớp lớp nối nhau bảo vệ mười vạn dặm Đại Hoang, người trong ba nghìn bộ lạc nhân tộc cũng đều lấy việc trở thành Luyện Khí Sĩ của Kiếm Môn làm vinh dự!\n" +
            "\n" +
            "Đại Hoang hiểm ác, nhân tộc lại yếu đuối, nếu không có Kiếm Môn bảo vệ thì e rằng ba nghìn bộ lạc đều đã tan thành khói bụi.\n" +
            "\n" +
            "Sơn môn to lớn chắn trước nơi ở của mấy vạn đệ tử ngoại môn.\n" +
            "\n" +
            "Luyện Khí Sĩ trong Kiếm Môn rất nhiều, những thiếu niên trong ba nghìn bộ lạc lên núi học nghệ nếu không có chống lưng thì đều sống ở đây. Những bộ lạc mạnh mẽ có nhiều Luyện Khí Sĩ thì thường trực tiếp thu người về nơi của mình.\n" +
            "\n" +
            "Chung Nhạc đã sớm rời giường, lúc này hắn đứng tại bên cạnh giếng dốc ngược một thùng nước từ đỉnh đầu xuống, sau đó lại rùng mạnh người để xua tan cơn buồn ngủ.\n" +
            "\n" +
            "Nước từ trên người hắn chảy xuống tạo nên vô số bọt nước trên thành giếng.\n" +
            "\n" +
            "Cơ thể của hắn rất rắn chắc, dáng người cân đối, làn da cũng căng tràn, quả thực rất có sức mạnh. Trên ngực hắn có ba vết cào rất sâu đã đóng vảy không rõ là do yêu thú nào lưu lại.\n" +
            "\n" +
            "Hắn quay người, sau lưng cũng có rất nhiều vết sẹo, trên tay trái cũng có, khắp người đều là thương tích lớn nhỏ, trông chẳng hề giống một thiếu niên mới mười lăm tuổi.\n" +
            "\n" +
            "Chung Nhạc đeo gùi thuốc lên rồi yên lặng rời khỏi sơn môn đi về chân núi.\n" +
            "\n" +
            "Đệ tử ngoại môn có địa vị rất thấp, cũng chỉ được học một vài công pháp đơn giản nhất, chỉ khi nào tu thành Linh mới có thể trở thành đệ tử kí danh của nội môn, làm môn hạ của Đường chủ trong nội môn, mới nhận được truyền thừa thực sự của Kiếm Môn. Có điều đệ tử ngoại môn không có người chống lưng mà muốn trở thành đệ tử kí danh của nội môn thì khó khăn tới mức nào cơ chứ?\n" +
            "\n" +
            "Đại Hoang ba nghìn bộ lạc, bộ lạc nhỏ cũng có vạn người, bộ lạc lớn thì có tới trăm vạn, chẳng ai trong số đó không muốn tới Kiếm Môn, không muốn trở thành đệ tử kí danh của nội môn, trở thành Luyện Khí Sĩ, vì thế cạnh tranh rất khốc liệt.\n" +
            "\n" +
            "Kiếm Môn có quy củ của Kiếm Môn, nếu trước mười sáu tuổi không thể tu thành Linh thì sẽ bị đuổi khỏi Kiếm Môn. Con cháu tộc trưởng các bộ lạc lớn nhỏ trong Đại Hoang tự nhiên đều được dùng linh đan diệu dược để tăng tiến tu vi, giúp họ trước mười sáu tuổi tu thành Linh.\n" +
            "\n" +
            "Dù cho tư chất có bình thường tới đâu thì chỉ cần có đủ linh dược cũng có thể dựng nên Luyện Khí Sĩ!\n" +
            "\n" +
            "Còn những người khác thì tất nhiên không thể nhận được đãi ngộ như thế, chỉ đành dựa vào bản thân cần cù tu luyện mà thôi.\n" +
            "\n" +
            "Nhà nghèo khó sinh quý tử, kẻ khốn cùng mấy ai thành Luyện Khí Sĩ đâu.\n" +
            "\n" +
            "Chung Nhạc xuất thân từ bộ lạc Chung Sơn, một bộ lạc còn không được tính là nhỏ, cũng bởi quanh năm liên miên bị thiên tai nên chỉ còn khoảng trăm người, có thể nói là bộ lạc nhỏ nhất trong Đại Hoang.\n" +
            "\n" +
            "Bộ lạc như vậy tự bảo vệ còn khó chứ nói gì tới việc đào tạo ra Luyện Khí Sĩ.\n" +
            "\n" +
            "Vì thế dù Chung Nhạc có nỗ lực hơn người khác cả trăm lần thì cũng còn xa lắm mới tu thành Linh.\n" +
            "\n" +
            "Lần này hắn xuống núi vì muốn tìm vài thứ linh dược trong khu rừng dưới chân núi nhằm luyện chế Vũ Linh đan tu bổ hồn phách.\n" +
            "\n" +
            "Vũ Linh đan là đan dược cực kì bình thường, nguyên liệu luyện chế cũng rất dễ kiếm, vậy mà ngay cả linh dược tầm thường như thế hắn cũng không mua nổi mà phải tự mình luyện chế.\n" +
            "\n" +
            "Tự hái thuốc, tự luyện thuốc, mất bao nhiêu thời gian, tất nhiên sẽ càng thua kém người khác,\n" +
            "\n" +
            "Chung Nhạc rời khỏi Kiếm Môn nửa canh giờ thì trời đã sáng tỏ, đệ tử nội môn và ngoại môn của Kiếm Môn đều đã thức dậy tu luyện. Bỗng nhiên trên núi vang lên tiếng chuông, theo đó là một vị Luyện Khí Sĩ mang theo khí tức mạnh mẽ bay xuống quát:\"Ma hồn âm chướng sắp hiện, đệ tử Kiếm Môn nghe lệnh, nghiêm cấm ra ngoài!\"','https://nae.vn/ttv/ttv/public/images/story/92b4f78268eb28127a0a9dd112d9ee3a42b273fca47d93421f400b928f621845.jpg',0)";
    private String SQLQuery0015 = "INSERT INTO truyen VALUES (null,'TINH THẦN BIẾN','Trời đang là mùa đông, tuyết rơi dầy khắp nơi, toàn kinh thành như được phủ thêm một lớp áo màu bạc. Viêm kinh thành rất lớn, khả dĩ có thể chứa số nhân khẩu là trăm vạn, người chịu trách nhiệm quản lý ba quận đông vực chính là Trấn Đông vương Tần Đức, phủ đệ cũng đặt tại kinh thành.\n" +
            "Phủ đệ của Trấn Đông vương chiếm một khoảng đất cực lớn, chính môn cả ngày lẫn đêm đều mở rộng. Cửa chính vào phủ rất rộng, có thể cùng lúc sáu bẩy người đi vào. Bạn đang đọc truyện tại Truyện FULL - www.Truyện FULL\n" +
            "Tại đại môn có luôn hai người đứng hai bên tả hữu của lối vào. Chỉ thấy đó là hai đại hán dũng mãnh thân trên không mặc y phục mà cởi trần. Họ đứng yên tựa như nham thạch điêu khắc mà thành, hai mắt luôn lạnh lùng nhìn lướt qua những người bộ hành đi ngang, sau lưng đeo huyết hồng sắc chiến đao, khí thế bức nhân.\n" +
            "Trong tiết đông lạnh giá hoa tuyết lất phất bay, nhiệt độ hạ xuống thấp mặt sông đều đã kết tinh thành băng nhưng hai đại hán hai thân trên vẫn cởi trần không hề mặc thêm gì cả. Nhưng điều làm cho mọi người kinh hãi chính là bên hai đại hán bất ngờ xuất hiện một lão hổ hung mãnh.\n" +
            "Lão hổ toàn thân hồng sắc rực lửa, thân thể dài khoảng hơn hai thước, đuôi ve vẩy trong không trung khiến không khí nhất thời xao động, từ đôi mắt hổ phát xuất tia hàn khí. Lão hổ chính thị được gọi là \"Liệt Hổ\"\n" +
            "Lúc đó từ Trấn Đông vương phủ xuất hiện hai đại hán giống như hai đại hán cởi trần đang đứng trước cửa, chỉ phân biệt nhờ vào liệt hổ hung mãnh họ dẫn theo, rồi họ thay đổi vị trí với nhau. Bên ngoài phủ đệ, dù là hào gia quý tộc hay là bình dân du dân tại Viêm kinh thành đều tự giác tránh đường đến gần Trấn Đông vương phủ.\n" +
            "Trong một tiểu viện u tĩnh tại Trấn Đông vương phủ.\n" +
            "Một trung niên nhân vận thanh y đang ngồi trên thạch kỷ, bên cạnh là một tiểu nam hài khả ái. Đứng trước mặt trung niên nhân là mười hai người, lão giả có, mĩ phụ có, hoặc là thanh niên cũng có…nhưng cả mười hai người này đều có điểm chung là đều mặc tử y.\n" +
            "\"Phụ vương, sao người lại gọi nhiều vị lão sư vậy?\" Hài tử Tần Vũ mới sáu tuổi đang ngồi trên đùi phụ thân, trong tay đang nghịch tuyết cầu, nghi hoặc nhìn phụ thân Tần Đức.\n" +
            "Tần Đức hòa ái nhìn nhi tử Tần Vũ, khẽ xoa đầu đoạn hướng về phía mười hai người kia điềm đạm nói: \"Các vị dậy dỗ Vũ nhi một khoảng thời gian, cũng đã tốn công không ít, không cần phải cân nhắc nữa, xin cho ta biết nhận xét. \"\n" +
            "Mười hai người kia nhìn nhau, sau cùng một lão giả có chòm râu bạc trắng tiến lên một bước, cung kính nói: \"Bẩm vương gia, chúng tại hạ đã quan sát về các phương diện, nhận thấy tam điện hạ đối với kỳ môn xảo kĩ rất có hứng thú, tuy nhiên đối với các vấn đề quyền thế không hề có chút hứng thú. Căn cứ vào những điều đó có thể phán đoán rằng tam điện hạ khó có khả năng phù hợp trở thành một người có vị trí cao.\"\n" +
            "Chỉ bằng vào vài ngày tiếp xúc với nam hài họ đã có thể đưa ra phán đoán như vậy, tựa như chém đinh chặt sắt. Tất nhiên Tần Đức trong lòng không thể hoài nghi được.\n" +
            "Tần Đức thở dài một tiếng, nhìn sang hài tử Tần Vũ ngây thơ chưa biết gì, cười khổ nói: \"Ta hiểu, Vũ nhi giống hệt mẹ nó, đối với quyền thế của thế tục không có một tia hứng thú, tuy nhiên nó sinh ra đã được định sẵn để trở thành…\"\n" +
            "Tần Đức đang nói đột nhiên ngừng lại, quay về phía sau phất tay nói: \"Thời gian qua đã làm phiền các ngươi nhiều, các người có thể li khai vương phủ.\" \"Vương gia, chúng tại hạ cáo từ!\"\n" +
            "Mười hai tử y nhân đồng thời cúi người, sau đó lần lượt rời khỏi tiểu trang viện u tĩnh.\n" +
            "Thời khắc đó, trong trang viện chỉ còn Tần Đức và nhi tử Tần Vũ. Tần Đức trầm mặc không nói gì, hồi lâu sau nhìn sang Tần Vũ, trong mắt chứa nhiều hàm ý khiến Tần Vũ mới chỉ là một đứa bé sáu tuổi không thể hiểu được.\n" +
            "\"Phụ vương, sao phụ vương không nói gì vậy?\" Tần Vũ trong lòng rất thắc mắc, nhưng nó vốn là đứa trẻ rất thông minh nên không tiếp tục làm phiền phụ thân. Từ nhỏ Tần Vũ đã mất mẫu thân, trong lòng nó thì phụ thân là trọng yếu nhất, ngoài ra còn có hai ca ca nữa. Rất lâu sau đó, Tần Đức vẫn ngồi như vậy và Tần Vũ cũng tiếp tục ngồi trên đùi của cha.\n" +
            "Đột nhiên, một tiếng hạc vang lên.\n" +
            "Chỉ thấy từ không trung một đạo bạch sắc tiên hạc bay tới, cưỡi phía trên là một trung niên tuấn nhã mang vẻ tiên phong đạo cốt, từ từ điều khiển tiên hạc hạ xuống trang viện. \"Phong huynh, Vũ nhi đan điền có vấn đề, huynh phải giúp ta tìm ra giải pháp…\" Tần Đức nhìn sang người trung niên, lo âu nói.\n" +
            "Phong Ngọc Tử nhìn sang Tần Đức, trong lòng đã tự nhiên đã hiểu rõ sự tình của hảo bằng hữu, chỉ thở dài: \"Vương gia, tại hạ đã nói rồi. Vũ nhi tại đan điền thập phần quái dị, hiện tại tu luyện nội công căn bản không có một tia hi vọng. Đã không thể tích tụ nội lực thì tự nhiên cũng không thể tu luyện nội công. Đan điền căn bản là do thiên sinh, trong hàng vạn người cũng không thể tìm được một người thứ hai nào như vậy, Phong Ngọc Tử ta thật sự không thể tìm ra giải pháp.\" Nghe lời đó, Tần Đức từ từ ngồi xuống trầm tư hồi lâu.\n" +
            "\"Phụ vương, nội lực là gì, đan điền không thể tích tụ nội lực là gì? Những vị lão sư lúc nãy cũng nói đến việc này, điều đó có nghĩa gì?\" Hài tử sáu tuổi Tần Vũ hai mắt nghi hoặc hỏi phụ thân. Lúc trước nó đã hỏi nhưng không được trả lời.','https://www.dtv-ebook.com/images/Cover/33977352572_322164bc19_o.png',0)";

    private String SQLQuery0016 = "INSERT INTO truyen VALUES (null,'ĐƯỜNG CHUYÊN','Con người luôn hay quên, cho nên sau khi trải qua một đoạn đường đời luôn bất giác dừng lại, nhìn về được mất thời gian trước đó. Được nhiều hơn mất chứng tỏ thời gian đó không lãng phí, phấn chấn chuẩn bị cho quãng đường tiếp theo. Mất nhiều hơn được thì có nghĩa là chẳng khác gì phân chó rồi, hận không thể sống lại làm lại. Nhưng đời này đâu ra thuốc hối hận, cho nên thứ mất đi thì sao lấy lại được nữa, dù anh có chạy nhanh hơn cả Lưu Tường chăng nữa. Đó là phép tắc phổ thông của thế giới này sao? Ở thời đại ngay cả định luật Newton đều có thể bị lật nhào, có một vài chuyện ngoài khoa học, cũng là điều có thể chấp nhận được.\n" +
            "\n" +
            "Vân Diệp vừa mới uống thuốc hối hận, chỉ là sức thuốc hơi mạnh một chút cho nên khi y phát hiện mình đang trần truồng đứng ở nơi hoang dã, chỉ biết đứng đần mặt ra đó.\n" +
            "\n" +
            "Vùng hoang dã này rất đẹp, thảm cỏ xanh mướt trải dài từ dưới chân hết tầm mắt, giữa lùm cây thi thoảng vươn ra mấy bông hoa dại, càng làm thảm cỏ tăng thêm vài phần đẹp đẽ.\n" +
            "\n" +
            "\"Phạch phạch phạch!\" Một con gà rừng từ trong bụi cỏ nhảy xồ ra, Vân Diệp hoảng sợ lui lại, lúc này mới hoang mang choàng tỉnh, đôi mắt khôi phục sự linh động, thần trí vẫn còn mơ hồ. \" Đây là đâu?\" Vân Diệp hỏi bản thân, mười phút trước y còn xách ba lô tìm kiếm hai người ngoại quốc mất tích ở sa mạc, hiện giờ lại trần như nhộng trên thảo nguyên. Chuyện này vượt tầm lý giải của y mất rồi, ngơ ngác nhìn mặt trời chói chang trên đầu, may còn có chút quen thuộc.\n" +
            "\n" +
            "Vân Diệp xác định mình vẫn ở Trái Đất, mấy cây du thấp lùn, cây hòe nằm rải rác, những cây ngải cao nửa thân người xen lẫn trong đó làm lòng y bình tĩnh hơn. Nếu vẫn là tây bắc thì có gì to tát đâu, quay về là được, đi thêm ít đường thôi mà. Vân Diệp phỏng chừng mình gặp phải lỗ giun, đi từ mặt này sang mặt kia mà thôi, vẫn nằm trong phạm vi sách vở. Sống ở vùng hoang nguyên tây bắc này hơn 15 năm rồi, đã thấy bão cát, thấy đá lở, thấy cát chảy, thấy đàn sói, bị kiến lớn cắn, thần kinh sớm đã vô cùng kiên cường, có gặp lỗ giun cũng chẳng lạ lùng.\n" +
            "\n" +
            "Gió bắc thổi qua, toàn thân rét run.\n" +
            "\n" +
            "Tây bắc vào tháng năm chưa có cái nhiệt độ cho người ta cởi truồng chạy rông, Vân Diệp còn nhớ trước khi đi mình bổ sung nước ở điểm lấy nước thứ sáu, nhìn thấy dưới đáy ao nước có ánh váng loáng qua, tưởng rằng khối vàng tự nhiên mới đưa tay ra mò, bị lực hút cực mạnh kéo tới đây.\n" +
            "\n" +
            "Chẳng trách lòng tham là nguồn gốc tội lỗi, Vân Diệp đánh mạnh lên tay phải, giờ thì gây họa rồi thấy chưa. Đưa tay che lấy chỗ hiểm yếu, y ngó quanh tìm kiếm ao nước, nhìn tới vòng thứ tư thì nghe tiếng nước róc rách truyền vào tai, y mừng húm chạy nhào về phía tiếng nước, chỉ thấy dòng suối khoan thai chảy trong lùm cỏ, đi dọc theo dòng suối, chẳng mấy chốc đã tới đầu nguồn, một đống quần áo chất ở miệng suối nổi lềnh bềnh trên mặt nước. Vân Diệp thu hồi tất cả quần áo của mình, kể cả tất, thậm chí còn có một cái xoong đít bằng mà Vân Diệp dùng để nấu mì gói. Vắt khô quần áo treo trên cành cây, Vân Diệp thở phào một hơi, cuối cùng không phải chạy khỏa thân nữa, nếu trả lại cả cái ba lô cho mình thì không đòi hỏi gì nữa.\n" +
            "\n" +
            "Vân Diệp vươn tay ra, nhìn đôi tay trắng nõn, so với tay của minh trước kia thì nhỏ hơn hẳn một cỡ, đây căn bản không phải đôi tay của người trưởng thành, y phát hiện vấn đề này trước đó rồi, chỉ là cố ép bản thân không nghĩ tới, đưa tay tóm lấy sợi tóc lòa xòa trên vai, giật thật mạnh, đau quá, không phải là mơ. Quay đầu nhìn khuôn mặt non nớt quen thuộc trong nước, Vân Diệp cứ lờ mờ cảm thấy chuyện không đơn giản như mình nghĩ. Truyện được lấy tại TruyenFull.vn\n" +
            "\n" +
            "Sinh tồn mới là điều quan trọng nhất, ở nơi hoang dã anh có thể không mặc quần áo, nhưng tuyệt đối không thể không đi giày, chạy nhảy là bản năng duy truyền từ tổ tiên, mặc dù có vụng về, nhưng là cách thức cứu mạng hữu hiệu nhất.\n" +
            "\n" +
            "Vân Diệp biết, nguồn nước ở chốn hoang dã không phải là nơi nghỉ chân an toàn, mang theo hi vọng mờ mịt nhất, y cố dồn nén sợ hãi trong nội tâm, nhìn dòng nước không chớp, hi vọng ông trời mở mắt trả lại cái ba lô cho mình. Đây là vùng đất chưa từng có dấu chân con người, hơi thở hồng hoang bao phủ khắp mảnh đất tĩnh mịch, Vân Diệp hiểu bản thân chỉ là một kỹ sư máy móc, nếu muốn sống sót không thể thiếu trang bị, có trang bị mới có thức ăn, mới có thể sống sót.\n" +
            "\n" +
            "Vân Diệp lúc lắc cái giày da nặng nề ở chân, đi giáy ướt sũng phải nói là khó chịu vô cùng, đi bước nào cũng phát ra những tiếng \"bẹp bẹp\", tay y cầm một cái gậy gỗ to như quả trứng gà, thi thoảng quất vào bụi cỏ lấy thêm can đảm cho bản thân. Có vẻ thần phật đầy trời đã nghe thấy lời cầu khẩn của y, từ đầu nguồn nước có một tấm vải bạt, Vân Diệp mừng rỡ cúi xuống cầm lấy ra sức kéo, một cái ba lô cao nửa người từ trong nước bật ra, Vân Diệp ôm chồm lấy nó, đây là số mạng. Y lấy xẻng ra, lòng yên tâm hẳn.','https://isach.info/images/story/cover/duong_chuyen__kiet_du_2.jpg',0)";

    private String SQLQuery0021 = "INSERT INTO truyen VALUES (null,'CẦU MA','Núi! Thanh sơn!\n" +
            "\n" +
            "Đây là một dãy núi lớn liên miên không dứt, như một con rồng còn sống kéo dài cả vùng đất mênh mông này, ở đấy có cỏ cây rậm rạp, còn có tiếng chim thú không ngừng vang lên.\n" +
            "\n" +
            "Xa xa nhìn lại, có thể thấy trên núi có một phần giống như được năm ngọn núi hợp lại, trông như năm ngón tay người giơ lên trời cao. Trong đó có một ngọn núi trung đoạn(1), một người thiếu niên đang tựa vào một khối đá lớn hơi bị lõm sâu vào để tránh nắng. Bên cạnh hắn có một cái sọt mây, trong đó có dựng một chút dược thảo, mùi thuốc tản phát ra lượn lờ khắp bốn phía.\n" +
            "\n" +
            "Thiếu niên này mi thanh mục tú, nhưng thân thể lại hơi ốm yếu, thoạt nhìn có chút suy nhược. Hắn mặc một cái áo được làm bằng da thú, cổ đeo một vòng thú cốt màu trắng hình trăng lưỡi liềm, đầu tóc rối bù được hắn dùng một sợi dây cây buộc lại.\n" +
            "\n" +
            "Hắn ngồi ở chỗ đó, tay cầm một quyển sách da do mười tấm da thú dính lại, chăm chú đọc lấy.\n" +
            "\n" +
            "- Tổ tông Man tộc, khai thiên tạo người, di lưu muôn đời đến nay...Vài người có sức mạnh lớn lao được gọi là Man Sĩ (2), họ có thể bay lên trời chui xuống đất, dời núi lấp biển...có Man Văn (3) thông thiên, hái được ngôi sao và nhật nguyệt...\n" +
            "\n" +
            "Thiếu niên đọc lấy, khẽ thở dài.\n" +
            "\n" +
            "- Không có Man thể, làm sao trở thành Man...Man Sĩ...Man Sĩ...Tô Minh, ngươi chỉ có thể hái được chút thảo dược, trở thành một y phu tầm thường mà thôi. Muốn trở thành Man sĩ ư? Thật xa xôi.\n" +
            "\n" +
            "Thiếu niên tự chế giễu chính mình, buông cuốn da thú trong tay xuống, nhìn về phương trời xa mà ngơ ngẩn.\n" +
            "\n" +
            "Hắn đã đọc qua cuốn da thú này rất nhiều lần rồi, nói hắn thuộc nằm lòng cũng không sai chút nào.\n" +
            "\n" +
            "- Trời là hình tròn, đất là hình vuông, như vô biên, phảng phất vô tận...\n" +
            "\n" +
            "Trong lúc lẩm bẩm, đầu hắn lại ảo tưởng đến thế giới trong cuốn sách. Bất chợt trời đã tối dần mà hắn không hề hay biết, mây đen bắt đầu xuất hiện từ chân trời xa xôi.\n" +
            "\n" +
            "Gió núi thổi qua mang theo hơi ẩm rơi xuống lá cây các cây cỏ trên núi, phát ra tiếng vang rắc..rắc..\n" +
            "\n" +
            "Khi nhìn thấy đám mây đen từ chân trời kéo đến đó, bỗng nhiên tinh thần Tô Minh rung lên.\n" +
            "\n" +
            "- A Công suy tính quả nhiên chính xác, quả thật hôm nay có Ô Long Tiên!\n" +
            "\n" +
            "Hai mắt Tô Minh phát sáng, hắn nhanh chóng đứng lên, lấy cuốn da thú nhét vào trong ngực, tay trái nhấc cái sọt mây vác lên lưng, thân thể thoáng một cái đã nắm lấy sợi dây leo bên cạnh một cách linh xảo, bắt đầu trèo lên đỉnh núi.\n" +
            "\n" +
            "Tuy thân thể người thiếu niên này suy nhược, nhưng lại có lực lượng vô cùng bền bỉ, trông như một con khỉ vậy. Mới chỉ nhảy lên mấy cái, hắn đã trèo lên được hơn mười trượng.\n" +
            "\n" +
            "Mây đen từ phương xa cuồn cuộn kéo đến, bên trong còn có tiếng sấm nổ vang lên, phảng phất như trời đất đang giận dữ với dãy núi này. Đám mây đen đó kéo dài khắp trời đất, càng ngày càng đến gần.\n" +
            "\n" +
            "Tô Minh vội vã leo nhanh hơn, hầu như ngay khi đám mây đen vừa kéo đến, hắn đã trèo đến một vị trí còn cách đỉnh núi khoảng mười trượng nữa.\n" +
            "\n" +
            "Ở ngay nơi đó có một khối đá kỳ lạ nhô ra, dường như được thiên nhiên tạo thành, ngay trên bề mặt khối đá đó có rất nhiều lỗ thủng lớn khoảng một nắm đấm. Khối đá kỳ lạ này được đặt ở ngay vị trí đó, nếu kết hợp với cả ngọn núi thì trông nó giống như đầu một con rắn khổng lồ vậy.\n" +
            "\n" +
            "Dưới khối đá kỳ lạ này có một mảnh đá nhô ra trông như một cái răng nanh, làm người nhìn thấy phải kinh hãi, cảm thấy có chút kỳ dị. Mà bởi vì khối đá này nhô ra khỏi ngọn núi như thế, nên rất khó leo qua để đi lên đỉnh núi, trừ khi ngươi có thể phi hành trong không trung.','https://img.dtruyen.com/public/images/large/caumaknV8qXlMh8.jpg',0)";
    private String SQLQuery0022 = "INSERT INTO truyen VALUES (null,'MA THIÊN KÝ','Đại Huyền quốc, quận Trừ Châu, trong một khu rừng rậm vắng vẻ gần thành Bạch Thủy, một bóng người gầy yếu dựa lưng vào một thân cây vừa thô to, hai chân ngồi giang rộng.\n" +
            "\n" +
            "Chủ nhân bóng người đó là một thiếu niên chừng mười ba mười bốn tuổi.\n" +
            "\n" +
            "Ngũ quan của hắn bình thường, nhưng sắc mặt đã tái nhợt, bộ quần áo làm bằng vải thô trên người hơi dài và rộng, không vừa người chút nào, bên cạnh đặt một thanh cương kiếm sáng loáng.\n" +
            "\n" +
            "Trên chuôi kiếm có vài vệt máu màu đen.\n" +
            "\n" +
            "Một bên bả vai của thiếu niên có một mảnh vải bố không biết là màu gì quấn quanh mấy vòng, trên đó còn có một ít máu thấm ra ngoài. Mi mắt thiếu niên khép chặt, cả người dựa vào thân cây không nhúc nhích, dường như đang thiêm thiếp ngủ.\n" +
            "\n" +
            "Đột nhiên một loạt tiếng động \"Soàn soạt\" rất nhỏ từ trong rừng rậm vọng ra, có một con gì đó đang nhanh chóng tiến gần tới vị trí thiếu niên.\n" +
            "\n" +
            "Thiếu niên mở to mắt, đứng bật dậy, đồng thời mũi chân cũng nhanh chóng nhảy sang phía bên cạnh.\n" +
            "\n" +
            "“Ầm” một tiếng.\n" +
            "\n" +
            "Chuôi cương kiếm kia bay lên trời, vững vàng rơi vào trong lòng bàn tay.\n" +
            "\n" +
            "Thiếu niên nhìn chăm chú một thoáng về phía phát ra âm thanh, sau đó liền nhảy lên không chút do dự. Hắn tung người về hướng ngược lại, sau mấy lần nhảy đã tiến vào sâu trong rừng rậm không thấy bóng dáng.\n" +
            "\n" +
            "Một lát sau, một đội võ sĩ mặc áo giáp màu đen cực dày, chia làm mấy tổ từ trong rừng cây đi ra.\n" +
            "\n" +
            "Đám giáp sĩ này chỉ có hơn hai mươi người, nhưng thân hình mỗi người đều cao lớn, mặt mũi hung hãn, rõ ràng đều là chiến sĩ hổ sói dạn dày kinh nghiệm chiến trận.\n" +
            "\n" +
            "Bọn chúng vừa mới đi ra từ trong rừng đã nghe thấy một tiếng quát khẽ, liền đứng nghiêm tại chỗ ngay lập tức.\n" +
            "\n" +
            "Cùng lúc đó, một gã giáp sĩ trẻ tuổi có gương mặt kiên nghị vội vã tiến lên vài bước, gã ngồi xổm xuống chỗ thiếu niên kia vừa nghỉ ngơi, dùng tay lật tới lật lui bùn đất xung quanh đó một lúc mới đứng dậy.\n" +
            "\n" +
            "\"Vương quân úy, đào phạm (tội phạm trốn tù) vừa đi không lâu, nếu bây giờ lập tức đuổi theo, nói không chừng còn có cơ hội đuổi kịp.\" Người giáp sĩ này quay người nói với một gã cự hán đầu trọc, người duy nhất không đội mũ giáp đen.\n" +
            "\n" +
            "Tuy rằng dáng những người ở đây đã tương đối cao lớn, nhưng nếu so sánh với cự hán cao xấp xỉ hai trượng này, rõ ràng là thấp hơn một đoạn khá dài, trông giống như trẻ con đứng trước mặt người lớn vậy.\n" +
            "\n" +
            "\"Hừ, không cần. Lúc này mấy thành chúng ta đã bày ra thiên la địa võng, cho dù tiểu tử này có giảo hoạt đi nữa cũng có mà chạy đằng trời. Bên kia, Tư Đồ quân úy đã chờ từ lâu rồi. Chúng ta chỉ cần duy trì thể lực chậm rãi qua đó là được rồi.\" Cự hán hừ một tiếng, nhìn về hướng thiếu niên chạy trốn một lát rồi nói.\n" +
            "\n" +
            "\"Quân úy đại nhân, người này là trọng phạm do châu quận chỉ đích danh, nếu như bắt được thì thực sự đã lập được công lớn, chẳng lẽ lại tặng không cho đám Tư Đồ quân úy sao?\" Giáp sĩ nghe vậy khẽ giật mình, giọng nói có chút do dự.\n" +
            "\n" +
            "\"Công lớn? Điều này cũng phải xem Tư Đồ lão tiểu tử kia có khả năng này hay không đã. Chúng ta cứ đuổi theo chậm một chút, nói không chừng vừa vặn có thể làm ngư ông đắc lợi.\" Cự hán đưa tay xoa xoa đầu trọc, mặt không biểu tình nói.\n" +
            "\n" +
            "\"Lời này của đại nhân là có ý gì? Nhân thủ của đại nhân Tư Đồ bên kia nhiều hơn một chút so với chúng ta, cho dù tiểu tử kia học được chút ít phương pháp đánh đấm, nhưng sao có thể thực sự kiên trì ở bên kia bao lâu được!\" Giáp sĩ trẻ tuổi có chút kinh ngạc.\n" +
            "\n" +
            "\"Dư Tín, thời gian ngươi ở bên ta cũng không ngắn, bình thường cũng có chút võ nghệ. Nhưng nếu một mình ngươi bị bộ khoái huyện nha vây công, trong một lần có thể đối mặt với tối đa bao nhiêu người mà vẫn có thể bình yên thoát thân?\" Cự hán không trực tiếp trả lời câu hỏi của giáp sĩ trẻ tuổi, mà ngược lại thâm ý hỏi một câu.','https://static.8cache.com/cover/o/eJzLyTDV13XO8ikz9UvJ80y10A_zKQxPy9C1yKnw1HeEgpySSP2QskwTv0Bvz4wqV_1yI0NT3QxjIyMAYTUS0w==/ma-thien-ky.jpg',0)";






    public databasedoctruyen(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    private String SQLQuery10= "INSERT INTO truyen VALUES (null,'MÃNG HOANG KỶ','\"Nhanh lên đi!\"\n" +
            "\n" +
            "\"Các người đều đã chết, đều biến thành quỷ rồi, nhanh lên.\"\n" +
            "\n" +
            "\"Ngươi là vương gia? Ngàn vạn con dân, ba vạn thiết kỵ? Vương gia ở dương gian xuống đến âm ty địa phủ ta đều chả là cái gì cả!\"\n" +
            "\n" +
            "Đùng!\n" +
            "\n" +
            "Đùng!\n" +
            "\n" +
            "Nguyên một đám quỷ binh cao to vạm vỡ, mặt mũi tràn đầy dữ tợn, gào thét quất roi. Cây roi bên trên lóe lên ánh chớp, quật lên đám quỷ hồn đằng kia, đặc biệt là cái tên quỷ hồn kêu gào mình là Vương gia ngay lập tức bị quất mấy chục roi, đến tận lúc quỷ hồn kia trở nên mỏng manh như sắp tiêu tán mới dừng lại.\n" +
            "\n" +
            "\"Có lẽ ta đã chết, đây là.... Nơi này là âm tào địa phủ?\" Kỷ Ninh hư vô xuất hiện, không khỏi hiếu kỳ quan sát hoàn cảnh lạ lẫm xung quanh liền nghe được tiếng kêu gào của tên Vương gia. Điều này làm cho Kỷ Ninh càng thêm nghi hoặc, \"Ngàn vạn con dân? Ba vạn thiết kỵ? Địa Cầu bây giờ là xã hội hiện đại, làm gì có ba vạn thiết kỵ?\"\n" +
            "\n" +
            "\"Nhanh lên!\" Phía trước tên Ngưu Đầu quỷ binh cao to vạm vỡ toàn thân hiện ra ánh sáng màu xanh đang nhìn chằm chằm vào Kỷ Ninh gầm gừ quát.\n" +
            "\n" +
            "Kỷ Ninh theo hướng hàng ngũ tiến lên.\n" +
            "\n" +
            "Vô số bóng người áo trắng xếp thành một hàng kéo dài đang chậm chạp đi tới. Cuối hàng dần xuất hiện thêm nhưng bóng người áo trắng từ hư vô mà đến. Những bóng người áo trắng này có lắc đầu thở dài, có gào khóc, có kêu gào tức giận, có kinh ngạc nghi hoặc.\n" +
            "\n" +
            "\"Cha ta là yêu vương Đại Tuyết Sơn, ngươi dám đánh ta! Ta ăn ngươi, gào!\"\n" +
            "\n" +
            "\"Đừng đánh!\"\n" +
            "\n" +
            "\"A!\"\n" +
            "\n" +
            "Những tên quỷ hồn vừa mới gia nhập cõi âm tối tăm đều tưởng là mình chưa chết. Mỗi lần bị quật là có không ít tiếng phẫn nộ kêu gào nhưng rất nhanh hiểu được làm sao bị quật... Bọn họ đã chết. Mặc cho khi còn sống phong quang cỡ nào thì sau khi chết đều là công dã tràng.\n" +
            "\n" +
            "...\n" +
            "\n" +
            "Thời gian trôi qua, Kỷ Ninh đã đi rất lâu theo hàng ngũ vô số quỷ hồn khác. Hắn không dám nói lời nào vì chỉ cần hé răng nửa lời ngay lập tức sẽ bị mấy tên đầu trâu mặt ngựa quật roi. Cứ im lặng đi như vậy thật lâu rồi, may là quỷ hồn không biết đói khát.\n" +
            "\n" +
            "Sau một ngày im lặng đi thật lâu.\n" +
            "\n" +
            "\"Kỷ Ninh!\" Một đạo thanh âm ầm ầm như tiếng sấm không ngừng chấn động xung quanh sinh ra tiếng vọng. Một đám dày đặc vô số quỷ hồn đều ngẩng đầu nhìn về hướng chân trời, Kỷ Ninh cũng nhìn theo. Chỉ thấy nơi chân trời đã có một đám mây đen cực lớn đang cuồn cuộn bay tới, đứng trên đám mây đen là một tên cự đại Ngưu Đầu quỷ thần xung quanh tản ra hắc quang.\n" +
            "\n" +
            "Tên cự đại Ngưu Đầu quỷ thần cao tầm vạn trượng như ngọn núi đứng sừng sững trên đám mây đen đang từ chân trời bay đến.\n" +
            "\n" +
            "\"Kỷ Ninh.\" Cự đại Ngưu Đầu quỷ thần từ phía trên mây đen nhìn xuống phía dưới, hai con mắt bắn ra hai chùm tia sáng màu vàng xẹt qua bầu trời chiếu thẳng xuống Kỷ Ninh đang đứng thẫn thờ ở phía dưới.\n" +
            "\n" +
            "Hai con mắt của cự đại Ngưu Đầu quỷ thần bắn ra kim quang quấn lấy người Kỷ Ninh, Kỷ Ninh liền biết mất ở hàng ngũ. Những tên đầu trâu mặt ngựa quỷ binh bình thường đều im thin thít còn đám quỷ hồn thì kinh ngạc đến thừ người ra sau một lúc mới kịp phản ứng.','https://truyenchu.vn/uploads/Images/mang-hoang-ky.jpg',0)";
    private String SQLQuery00= "INSERT INTO truyen VALUES (null,'SÁT THẦN','Thạch Nham đột nhiên tỉnh lại, liền cảm thấy đầu đau như búa bổ, đầu như đổ chì nặng nề vô cùng.\n" +
            "\n" +
            "Đây là một thạch động âm u, thạch động cỡ sân bóng rổ, trên mặt đất chồng chất bạch cốt có đến mười thi thể còn mới ăn mặc quái dị rải rắc bên cạnh hắn. Y phục trên thi thể còn rất mới xem ra chết cũng chưa được lâu lắm.\n" +
            "\n" +
            "Đây là đâu? Là quần đảo Bahamas ở biển Caribê sao?\n" +
            "\n" +
            "Thạch Nham là người ham thích vận động cực hạn cuồng nhiệt đến mức gần như tẩu hỏa nhập ma. Hai mươi bảy tuổi mẫu thân qua đời sớm, phụ thân tích lũy tài phú cả đời, lại vì bệnh ung thư lúc trung niên mà bỏ mình. Nên hắn sớm kế thừa gia tài tiêu cả đời cũng không hết.\n" +
            "\n" +
            "Người khác đau khổ truy tìm thứ gì đó của cuộc đời mà hắn lại có dễ như trở bàn tay, đã có sẵn ngay từ đầu rồi.\n" +
            "\n" +
            "Lúc tuổi trẻ tốt đẹp lại trì trệ tìm không được mục tiêu, loại cảm giác giàu có nhưng không có cách tiếp cận, khiến hắn cứ buồn bực không vui.\n" +
            "\n" +
            "Mãi đến năm mười bảy tuổi, hắn tiếp xúc đến vận động cực hạn mới cảm thấy đời người đột nhiên thú vị. Bởi vì trong tay nắm giữ gia tài kếch xù, hắn có thể ở trong đám người không tầm thường phung phía tham gia vận động.\n" +
            "\n" +
            "Tay không leo núi, bắt cá sấu, nhảy bungee, nhảy dù trên không, trượt ván trên núi lửa, nhảy cầu từ vách núi, xe trượt băng và rất nhiều vận động cực hạn điên cuồng đều trở thành lạc thú lớn nhất của đời hắn.\n" +
            "\n" +
            "Hắn hưởng thụ loại mạo hiểm giữa ranh giới sinh tử làm cho huyết mạch sôi trào tới mức ngạt thở!\n" +
            "\n" +
            "Mười năm qua, tất cả vận động cực hạn điên cuồng nhất hung hiểm nhất hắn đều lần lượt thử. Chỗ nào nguy hiểm chết người hắn liền chạy đến chỗ đó.\n" +
            "\n" +
            "Mười năm vận động cực hạn, khiến cho tố chất thân thể Thạch Nham cực kỳ vĩ đại. Mấy trăm lần thể nghiệm tiếp cận tử vong, khiến thần kinh hắn cứng cỏi đến mức cực kỳ biến thái, hắn tự giễu mình là người cách tử thần gần nhất.\n" +
            "\n" +
            "Thám hiểm Hố xanh quần đảo Bahamas biển Caribê, là cực hạn khiêu chiến cuối cùng của hắn. Những Hố xanh đó có chiều sâu đến vài trăm mét, có một số còn phức tạp như mê cung. Hơn nữa động tác hơi mạnh một chút, nước bùn dưới đáy hố liền nổi lên, mặc kệ mang bao nhiêu đèn chiếu sáng ngươi cũng không thấy rõ phương hướng.\n" +
            "\n" +
            "Cho nên mặc kệ là cao thủ lặn bản lĩnh cao siêu cỡ nào, trước khi nhảy vào Hố xanh phải buộc một dây thép. Đây là con đường sống của nhà thám hiểm Hố xanh, dây thép kéo hết có ý nghĩa là đã lặn đến đáy. Nếu còn muốn tiếp tục bơi tiếp, đồng nghĩa với tự sát, bởi vì ai cũng không thể không dùng dây thép bơi ra \"Mê cung\". Theo công tác thống kê của ngành hàng hải Bahamas, trung bình hàng năm có 20 người cao thủ lặn xuống giới hạn chết ở Hố xanh, đa số đều vì lạc đường mà chết.\n" +
            "\n" +
            "Vì thám hiểm cực hạn nguy hiểm nhất toàn cầu, Thạch Nham chủ động vứt bỏ dây thép được xưng là con đường sống. Tiến hành điên cuồng mạo hiểm xem như tự sát, cuối cùng bị lạc ở trong Hố xanh thần bí.\n" +
            "\n" +
            "Mà bị lạc trong Hố xanh có nghĩa là tử vong...','https://yymedia.codeprime.net/media/novels/2021-04/a264b58c10.jpg',0)";
    private String SQLQuery01= "INSERT INTO truyen VALUES (null,'LINH VỰC','Trấn Lăng Gia\n" +
            "\n" +
            "Sáng sớm khi trời vừa mới tờ mờ sáng, thiếu niên Tần Liệt đờ đẫn rời giường, sau khi rửa mặt chải đầu qua loa, hắn lập tức đi về phía Lăng Gia.\n" +
            "\n" +
            "Tần Liệt mặc áo tang vải thô, dáng người gầy yếu, bộ dáng cũng coi như là có chút thanh tú, nhưng ánh mắt thì mờ mờ ảo ảo không có chút thần thái nào tạo cho người ngoài có cảm giác hắn không hề có linh hồn.\n" +
            "\n" +
            "Ở ven đường có không ít võ giả thanh niên ở trấn Lăng Gia đã rời giường từ sớm. Rất nhiều người thấy Tần Liệt thì đều mỉm cười với ý tốt.\n" +
            "\n" +
            "\"Tần Liệt dậy sớm thế!\"\n" +
            "\n" +
            "Lăng Phong thấy Tần Liệt đi vào thì nhếch miệng cười, đưa tay vẫy một cái.\n" +
            "\n" +
            "Đáng tiếc, Tần Liệt như không nghe thấy tiếng người ngoài, vẻ mặt vẫn đờ đẫn, bước chân vẫn không dừng lại, lách người qua Lăng Phong rồi lại đi tiếp về phía trước.\n" +
            "\n" +
            "\"Phong đại ca, huynh thừa hơi nói với kẻ ngu làm gì? Đã biết hắn sẽ không bao giờ đáp lời, vậy mà hôm nào huynh cũng chào hỏi lúc sáng với hắn làm gì?\" Lăng Dĩnh mặc trang phục sáng vàng, dáng người đẫy đà, mười tám đôi mươi, lớn lên có chút xinh đẹp, bộ ngực cao cao nhô lên làm người khác không khỏi hoài niệm. Nàng khinh miệt nhìn về phía Tần Liệt, hờ hững buông ra hai câu.\n" +
            "\n" +
            "Mấy tên nam nữ Lăng gia ở bên cạnh nghe được lời của Lăng Dĩnh thì vẫn mang vẻ mặt lạnh lùng, lòng sinh chán ghét.\n" +
            "\n" +
            "Lăng Dĩnh hừ một tiếng, coi như không nhìn thấy mọi người xung quanh, ưỡn bộ ngực lên, cười với vẻ khinh thường.\n" +
            "\n" +
            "Phần lớn người trong đám nam nữ kia đều chỉ có cảnh giới luyện thể cấp bốn năm nên căn bản không được một người luyện thể cấp sáu như Lăng Dĩnh để vào mắt.\n" +
            "\n" +
            "Trong mắt nàng chỉ có Lăng Phong đạt luyện thể cấp bảy mà thôi.\n" +
            "\n" +
            "Lăng Phong hơi nhíu mày: \"Tiểu Dĩnh, ngươi tới trấn Lăng Gia chưa được bao lâu, không chịu ân huệ của ông nội Tần Liệt cho nên cái nhìn có chút hơi khác. Ta không trách ngươi. Nhưng những binh sĩ Lăng gia chúng ta, lúc ông nội Tần Sơn còn khỏe mạnh, đã được giúp không ít rồi. Cho dù hai năm trước ông nội Tần Sơn mất thì ơn giúp đỡ của ông ấy vẫn khắc sâu trong tâm khảm chúng ta. Hi vọng ngươi có thể nể mặt chúng ta mà tôn trọng Tần Liệt.\"\n" +
            "\n" +
            "Lăng Dĩnh hơi ngây ra: \"Ông nội của hắn rất lợi hại sao?\"\n" +
            "\n" +
            "\"Ông nội Tần Sơn là luyện khí sư!\" Lăng Phong quát nhẹ.\n" +
            "\n" +
            "Lăng Dinh cảm thấy hơi kính nể. \"Có Phàm cấp, Huyền cấp, Địa cấp, Thiên cấp, Thần cấp. Mỗi cấp lại phân ra làm bảy loại. Ông ta là luyện khí sư loại nào? Ông ấy luyện linh khí cho Lăng gia sao?\"\n" +
            "\n" +
            "\"Không đâu.\" Lăng Phong lắc đầu. \"Ông nội Tần Sơn chỉ biết chữa linh khí Phàm cấp loại năm trở xuống thôi.\"\n" +
            "\n" +
            "\"Ây da, dọa người ta à. Ta còn tưởng lợi hại thế nào, hóa ra cũng chẳng có gì lắm.\" Lăng Dĩnh vỗ vỗ bộ ngực đẫy đà, cười với vẻ hơi khoa trương.\n','https://nae.vn/ttv/ttv/public/images/story/5587f9be987ad254dc83b0debfd18ee1397f23ddbab8b5297a5db640009f58df.jpg',0)";
    private String SQLQuery02= "INSERT INTO truyen VALUES (null,'TẠO HÓA CHI MÔN','Trên một con đường xi măng không rộng rộng rãi, một chiếc xe đường hổ lại chạy cực kỳ bình ổn. Hai bên thôn dân thấy chiếc xe đường hổ này, đều có chút nao núng đứng ở bên ngoài con đường, nhường cho chiếc xe đi qua. Những thôn dân này không nhận ra đây là loại xe gì, lại có thể cảm giác được người ngồi trên xe này khẳng định không bình thường.\n" +
            "\n" +
            "Đối với Đông Hợp thôn mà nói, nếu mà không phải thôn làm công trình, con đường xi măng này cũng sẽ không tồn tại.\n" +
            "\n" +
            "Xe Đường hổ ở cách bên ngoài Đông Hợp thôn còn chừng hơn một trăm thước liền ngừng lại, người lái xe rất cẩn thận, dừng xe ở trên một khối đất hoang nhô ra phía ngoài đường xi măng.\n" +
            "\n" +
            "Tài xế đỗ xe xong, sau đó nhanh chóng xuống xe ra sau mở cửa xe, hai thiếu nữ ngồi ở phía sau xe liền bước ra.\n" +
            "\n" +
            "Xuống xe trước tiên là một người thiếu nữ mặc váy xanh, tóc cài tùy ý xõa sau vai. Váy dài qua đầu gối, càng phụ trợ ra cho vóc người duyên dáng yêu kiều cùng da thịt trắng nõn của nàng.\n" +
            "\n" +
            "Nàng chỉ là tùy ý đứng ở ven đường, nhưng thật giống như đã sáp nhập vào toàn bộ vùng quê, như từ giữa bức tranh đi ra giống nhau, thoát tục xuất trần.\n" +
            "\n" +
            "Xuống xe tiếp theo chính là một thiếu nữ mặc quần jean màu lam, áo Hồ Điệp màu trắng. Quần jean hơi có chút bó sát càng làm nổi bật bờ eo thon thân cùng cái mông vun cao của nàng, hơn nữa cô gái này có một mái tóc ngắn, có vẻ khí tức thanh xuân bức người. Nếu mà không phải môi hơi chút mỏng một chút, nàng  tuyệt đối là một mỹ nữ làm cho ánh mắt người ta dời ra không được. Coi như là như vậy, nàng cũng đã là một thiếu nữ vô cùng xinh đẹp.\n" +
            "\n" +
            "Sau khi xuống xe người thiếu nữ tóc ngắn nhìn thoáng qua tuyệt mỹ dung nhan của thiếu nữ váy xanh đang đứng ở ven đường, khóe mắt hiện lên một tia đố kị căn bản là không cảm thấy được.\n" +
            "\n" +
            "\"Tễ Vân, nơi này chính là Đông Hợp thôn sao?\" Thiếu nữ váy xanh kia nhìn một vài ngôi nhà ngói, nhà gác đơn độc xa xa mất trật tự không hề có quy hoạch hỏi.\n" +
            "\n" +
            "Thiếu nữ tóc ngắn được gọi là Tễ Vân cười khúc khích, \"Dĩ nhiên, tuyệt đối sẽ không có sai, ta đã tới một lần. Đi thôi, Mộ Uyển, ta dẫn ngươi đi tới trong nhà Ninh Tiểu Thành nhìn xem. Được rồi, đứng ở chỗ này cũng có thể thấy, ngươi nhìn thấy ngôi nhà ngói ở góc ngoài cùng phía đông chỗ kia chứ? Đó chính là nhà của Ninh Tiểu Thành.\"\n" +
            "\n" +
            "Điền Mộ Uyển theo phương hướng của ngón tay Tễ Vân nhìn sang, thời điểm nàng nhìn thấy một ngôi nhà ngói thấp bé bằng đất, lập tức liền nhíu mày. Thôn này thoạt nhìn cũng không giàu có, thế nhưng phòng ở bằng đất cũng ít thấy, coi như là kém nhất cũng là phòng gạch ngói. Nàng cau mày không phải là bởi vì nhà của Ninh Tiểu Thành làm bằng đất, mà là bởi vì Ninh Tiểu Thành trong nhà nghèo như vậy, hắn xuất thủ lại quá khoa trương đi.\n" +
            "\n" +
            "\"Mộ Uyển, chúng ta đi qua xem một chút đi.\" Tễ Vân thấy Điền Mộ Uyển cau mày, lập tức liền nói.\n" +
            "\n" +
            "Điền Mộ Uyển gật đầu, quay đầu hướng tài xế kia nói, \"Mạnh thúc, ngươi liền ở chỗ này chờ chúng ta được rồi.\"','https://truyenchu.vn/uploads/Images/tao-hoa-chi-mon.jpg',0)";
    private String SQLQuery03= "INSERT INTO truyen VALUES (null,'VŨ ĐỘNG CÀN KHÔN','Khi Lâm Động hao hết khí lực mở ra đôi mi nặng trĩu, nhất thời căn phòng đơn sơ mà sạch sẽ xuất hiện trong mắt, hình ảnh quen thuộc làm hắn ngẩn người ra, chợt vội quay đầu lại, quả nhiên nhìn thấy hai đạo thân ảnh, một nam một nữ ngồi bên cạnh bàn.\n" +
            "\n" +
            "\"Cha, mẹ ...\"\n" +
            "\n" +
            "Nhìn hai đạo thân ảnh kia, Lâm Động vội gắng gượng tinh thần, nhỏ giọng gọi.\n" +
            "\n" +
            "\"Động nhi, con tỉnh?\"\n" +
            "\n" +
            "Nghe thấy tiếng kêu, nàng kia quay đầu sang, nhìn thấy Lâm Động vừa mở mắt, nhất thời vui sướng nói.\n" +
            "\n" +
            "Cả người nữ tử kia có chút mộc mạc, nhìn qua chừng ba mươi, khuôn mặt hơi thanh tú làm người ta có loại cảm giác dịu dàng nhu hòa, mà nàng đúng là mẫu thân Lâm Động - Liễu Nghiên.\n" +
            "\n" +
            "\"Học nghệ chưa tinh mà đã đánh nhau với người ta, tự chuốc lấy khổ.\"\n" +
            "\n" +
            "Ngồi bên cạnh nữ tử là một vị nam tử nhìn qua chừng ba bốn mươi tuổi, thân thể có chút đơn bạc, có thể mơ hồ nhìn thấy một chút sắc bén trong ánh mắt hắn, chẳng qua giống như có thương tích trong người, khuôn mặt hơi có vẻ tái nhợt, che lấp hơn nửa tia sắc bén kia, hắn là phụ thân Lâm Động - Lâm Khiếu.\n" +
            "\n" +
            "Lâm Động hiển nhiên có chút e ngại với vị phụ thân xưa nay nghiêm khắc này, rụt cổ lại, chợt lại có chút không phục nói: \"Ai bảo những tên kia đứng trước mặt ta mắng cha là phế vật...\"\n" +
            "\n" +
            "Khi nói chuyện, Lâm Động sờ sờ ngực, nơi đó vẫn còn chút nhói đau, không khỏi oán hận nghiến răng, vốn hôm nay là một lần khảo thí của Lâm gia, mà hắn cũng đi kiểm tra nhẹ, vì mới bắt đầu tu luyện chỉ hơn nửa năm nên thành tích cũng không bao nhiêu, mà hắn cũng không để trong lòng, nếu cho hắn có thời gian và điều kiện tu luyện như những người khác thì hắn tin rằng mình sẽ chẳng yếu hơn người khác.\n" +
            "\n" +
            "Mà khi khảo thí chấm dứt, Lâm Động đang chuẩn bị dẹp đường hồi phủ thì vài tên ngày thường quan hệ không tốt, vốn dĩ hắn không để ý tới nhưng lại không nhịn được sự cố ý khiêu khích của đối phương, phẫn nộ, tuổi trẻ, tất nhiên Lâm Động không nhịn được ra tay, mà kết quả cũng thực rõ ràng, hắn trực tiếp bị đánh một trận, còn bị ngất đi.\n" +
            "\n" +
            "\"Lâm Sơn, ngươi nhớ kỹ cho ta, lần sau ta không đánh ngươi thành đầu heo thì ta không mang họ Lâm nữa!\"\n" +
            "\n" +
            "Lâm Sơn trong lời Lâm Động chính là người khởi xướng chuyện này, cũng là địch nhân lớn nhất hiện tại trong lòng Lâm Động, bởi vì quan hệ phụ thân hai bên cực kỳ ác liệt nên Lâm Sơn kia cũng thường xuyên đến gây sự với Lâm Động, mà lần này cũng là một trong số đó.\n" +
            "\n" +
            "Hung hăng nghiến răng, nhưng ngay sau đó Lâm Động đột nhiên ủ rũ lại, tuy Lâm Sơn kia đáng giận đến cực điểm nhưng nói gì thì nói, hiện tại tên kia cũng đã Thối Thể tứ trọng, thành tích này ở trong đám tiểu bối Lâm gia cũng xem như đi đằng trước, so với thực lực Thối Thể nhị trọng như Lâm Động hắn thì quả thật mạnh hơn không ít.\n" +
            "\n" +
            "Tu luyện nhất đạo, luyện thể vi tiên, hết thảy đều bắt đầu từ thân thể của mình, nhân thể vốn là thứ huyền ảo khó lường nhất trong thiên địa.','https://truyenaudio247.com/Files/Poster/vu-dong-can-khon.jpg',0)";
    private String SQLQuery04= "INSERT INTO truyen VALUES (null,'Thần Mộ', 'Xuyên suốt vũ trụ hồng hoang, bền vững như thiên địa cao xanh... dẫu thoát được sáu kiếp luân hồi, cũng khó bề chạy khỏi thần ma vi giới.\n" +
            "\n" +
            "Thần Ma Lăng Viên ở ngay chính giữa Thiên Nguyên đại lục, đây cũng là nơi an táng những nhân vật mạnh nhất trong lịch sử loài người, khu mộ phần được phân loại theo cấp độ mạnh yếu, càng vào sâu càng mạnh. Trong mỗi ngôi mộ nếu không phải thần linh thì cũng là yêu ma thượng cổ, đây cũng chính là nơi thần thánh và ma quỷ yên nghỉ ngàn thu.\n" +
            "\n" +
            "Thảm cỏ Lăng Viên xanh mượt mà, chung quanh có hàng trăm loại hoa, tuy không lớn như một khu rừng, nhưng gọi là vườn cũng không ổn. Bên ngoài Lăng Viên có giống cây cao tên gọi Tuyết Phong, chỉ mọc duy nhất ở nơi đây, tương truyền do linh khí của thần ma hóa thành.\n" +
            "\n" +
            "Cây Tuyết Phong cành lá xanh um tươi tốt, khẽ đung đưa trong gió, tựa hồ đang hồi tưởng thời khắc huy hoàng thuở xưa, hoa trắng rụng như tuyết, nhẹ nhàng phiêu sái giữa không trung, trông giống nước mắt thần linh, khiến người ta cảm thấy bi thương vô ngần.\n" +
            "\n" +
            "Khu mộ trải qua ngày nắng đêm đen với những cảnh tượng tương phản.\n" +
            "\n" +
            "Ban ngày tiên khí dầy đặc, ánh sáng thanh khiết chiếu rỡ ràng trên từng ngôi mộ. Trong ánh sáng ấy, người ta có thể trông thấy linh hồn bất diệt của các thần ma thuở xa xưa, thậm chí còn trông thấy thiên sứ nhảy múa, nghe thấy tiên tử hát ca. Cả khu Lăng Viên chìm đắm trong một bầu không khí thần thánh.\n" +
            "\n" +
            "Ban ngày là thiên đường như thế, song ban đêm nơi đây lại là địa ngục.\n" +
            "\n" +
            "Mỗi khi mặt trời xuống núi, bóng đêm bao trùm khắp nơi, ma khí hắc ám từ dưới lòng đất hùng dũng kéo lên, khiến cho tinh tú lu mờ, mặt trăng ảm đạm, khiến cho trời đất thê lương. Lúc ấy, hung thần trong truyền thuyết xuất hiện, ảo ảnh ác ma vẫy vùng khắp Lăng Viên, không gian tràn ngập tiếng hú dài của hung linh thời viễn cỗ, tiếng hú thê thảm khiến da đầu người ta tê cả đi.\n" +
            "\n" +
            "Tuy vậy, thần thánh hay ma quái thì cũng chỉ lộ diện ở mặt phía đông Lăng Viên. Mặt phía tây khác hẳn, nơi ấy là chỗ tu luyện của cộng đồng tế bái. Buổi sáng môn nhân thường lên tế điện, những khi đúng dịp viếng niệm đặc biệt thì cả đêm họ cũng đến. Dần dần nhân gian có câu: phương đông thây người, phương tây sư pháp.\n" +
            "\n" +
            "Tại Lăng Viên, hoàng hôn là lúc an bình nhất, mộ địa hoàn toàn yên ắng, đến một tiếng động nhỏ cũng không có.\n','https://www.dtv-ebook.com/images/truyen-online/ebook-than-mo-prc-pdf-epub.jpg',0)";
    private String SQLacc1 = "INSERT INTO taikhoan (tentaikhoan,matkhau,email,phanquyen) VAlUES ('account1','12345678','account1@gmail.com',1)";
    private String SQLacc2 = "INSERT INTO taikhoan (tentaikhoan,matkhau,email,phanquyen) VAlUES ('account2','12345678','account2@gmail.com',1)";
    private String SQLacc3 = "INSERT INTO taikhoan (tentaikhoan,matkhau,email,phanquyen) VAlUES ('account3','12345678','account3@gmail.com',1)";
    private String SQLacc4 = "INSERT INTO taikhoan (tentaikhoan,matkhau,email,phanquyen) VAlUES ('account4','12345678','account4@gmail.com',1)";
    private String SQLacc5 = "INSERT INTO taikhoan (tentaikhoan,matkhau,email,phanquyen) VAlUES ('account5','12345678','account5@gmail.com',1)";
    private String SQLacc6 = "INSERT INTO taikhoan (tentaikhoan,matkhau,email,phanquyen) VAlUES ('account6','12345678','account6@gmail.com',1)";
    private String SQLacc7 = "INSERT INTO taikhoan (tentaikhoan,matkhau,email,phanquyen) VAlUES ('account7','12345678','account7@gmail.com',1)";
    private String SQLacc8 = "INSERT INTO taikhoan (tentaikhoan,matkhau,email,phanquyen) VAlUES ('account8','12345678','account8@gmail.com',1)";
    private String SQLacc9 = "INSERT INTO taikhoan (tentaikhoan,matkhau,email,phanquyen) VAlUES ('account9','12345678','account9@gmail.com',1)";
    private String SQLacc10 = "INSERT INTO taikhoan (tentaikhoan,matkhau,email,phanquyen) VAlUES ('account10','12345678','account10@gmail.com',1)";
    private String SQLacc11 = "INSERT INTO taikhoan (tentaikhoan,matkhau,email,phanquyen) VAlUES ('account11','12345678','account11@gmail.com',1)";
    private String SQLacc12 = "INSERT INTO taikhoan (tentaikhoan,matkhau,email,phanquyen) VAlUES ('account12','12345678','account12@gmail.com',1)";
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQLQuery);
        db.execSQL(SQLQuery1);
        db.execSQL(SQLadmin);
        db.execSQL(SQLacc1);
        db.execSQL(SQLacc2);
        db.execSQL(SQLacc3);
        db.execSQL(SQLacc4);
        db.execSQL(SQLacc5);
        db.execSQL(SQLacc6);
        db.execSQL(SQLacc7);
        db.execSQL(SQLacc8);
        db.execSQL(SQLacc9);
        db.execSQL(SQLacc10);
        db.execSQL(SQLacc11);
        db.execSQL(SQLacc12);
 //       db.execSQL(SQLQuery2);
//        db.execSQL(SQLQuery3);
        db.execSQL(SQLQuery8);
        db.execSQL(SQLQuery88);
        db.execSQL(SQLQuery8888);
        db.execSQL(SQLQuery88888);
        db.execSQL(SQLQuery001);
        db.execSQL(SQLQuery002);
        db.execSQL(SQLQuery003);
        db.execSQL(SQLQuery004);
        db.execSQL(SQLQuery005);
        db.execSQL(SQLQuery006);
        db.execSQL(SQLQuery007);
        db.execSQL(SQLQuery008);
        db.execSQL(SQLQuery009);
        db.execSQL(SQLQuery0010);
        db.execSQL(SQLQuery0011);
        db.execSQL(SQLQuery4);
        db.execSQL(SQLQuery5);
        db.execSQL(SQLQuery6);
        db.execSQL(SQLQuery7);
        db.execSQL(SQLQuery0012);
        db.execSQL(SQLQuery0014);
        db.execSQL(SQLQuery0015);
        db.execSQL(SQLQuery0016);
        db.execSQL(SQLQuery0021);
        db.execSQL(SQLQuery0022);
//        db.execSQL(SQLQuery11);
//        db.execSQL(SQLQuery12);
        db.execSQL(SQLQuery13);
        db.execSQL(SQLQuery10);
        db.execSQL(SQLQuery02);
        db.execSQL(SQLQuery00);
        db.execSQL(SQLQuery01);
        db.execSQL(SQLQuery03);
        db.execSQL(SQLQuery04);
        db.execSQL(SQLQueryYT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void AddTaiKhoan(TaiKhoan taiKhoan){
        SQLiteDatabase db = this.getWritableDatabase();

        //không thể lưu trực tiếp xuống insert nên thông qua contentvalues
        ContentValues values = new ContentValues();
        values.put(TEN_TAI_KHOAN,taiKhoan.getmTenTaiKhoan());
        values.put(MAT_KHAU,taiKhoan.getmMatKhau());
        values.put(EMAIL,taiKhoan.getmEmail());
        values.put(PHAN_QUYEN,taiKhoan.getmPhanQuyen());

        db.insert(TABLE_TAIKHOAN,null,values);
        //đóng lại db cho an toàn
        db.close();
        //Log.e("Add Tai Khoan ","thành công");
    }

    public Cursor getDataus(){
        SQLiteDatabase db = this.getReadableDatabase();
        String laz= "select * from taikhoan where phanquyen = 1 ORDER BY idtaikhoan DESC";
        Cursor res =  db.rawQuery( laz, null );
        return res;
    }


    //Lấy tất cả tài khoản
    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_TAIKHOAN +" ORDER BY idtaikhoan DESC", null );
        return res;
    }

    public Cursor getSSTK(String taikhoan ){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_TAIKHOAN + " where tentaikhoan = \'"+taikhoan+"\'", null);
        return res;
    }


    //Thêm truyện
    public void AddTruyen(Truyen truyen){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN_TRUYEN,truyen.getTenTruyen());
        values.put(NOI_DUNG,truyen.getNoiDung());
        values.put(IMAGE,truyen.getAnh());
//        values.put(SL_YEUTHICH,truyen.getsoluongyt());
        values.put(SL_YEUTHICH,0);
        db.insert(TABLE_TRUYEN,null,values);
        db.close();
        Log.e("Add Truyện : ","Thành công");
    }

    //Thêm đánh giá
    public void AddDanhGia(DanhGia danhgia){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOI_DUNG_DANH_GIA,danhgia.getNoiDungDanhGia());
        // values.put(ID_TAI_KHOAN,danhgia.getIDTK());
        values.put(TEN_TAI_KHOAN,danhgia.getTenTK());
        values.put(TEN_TRUYEN, danhgia.getTenTruyen());
        db.insert(TABLE_DANH_GIA,null,values);
        db.close();
        Log.e("Add đánh giá : ","Thành công");
    }
    public void AddYeuThich(yeuthich yeuthich) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(TEN_TAI_KHOAN_YT,yeuthich.getTentaikhoan());
        values.put(TIEU_DE_YT,yeuthich.getTieude());
        String l="SELECT * from dsyeuthich WHERE tieude = '"+yeuthich.getTieude()+"' AND tentaikhoan = '"+yeuthich.getTentaikhoan()+"'";
        Cursor cursor1 = db.rawQuery(l,null);
        try {
            cursor1.moveToFirst();
            int id=cursor1.getInt(0);
        } catch (Exception e)
        {
            db.insert(TABLE_DS_YEU_THICH,null,values);
            db.close();
            String y="(soluongyt + 1)";
            SQLiteDatabase db2 = this.getWritableDatabase();
            db2.execSQL("UPDATE truyen set  soluongyt= "+y+ " WHERE tieude='"+yeuthich.getTieude()+"'");
            db2.close();
            Log.e("Add yêu thích : ","Thành công");
        }

    }


    //Lấy tin mới nhất
    public Cursor getData1(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery( "select * from "+TABLE_TRUYEN+" ORDER BY "+ID_TRUYEN+" DESC LIMIT 4" , null );
        return res;
    }


//    public Cursor getData4(String tentk){
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor res =  db.rawQuery( "select DISTINCT  * from dsyeuthich WHERE tentaikhoan = '"+tentk+"'" , null );
//        return res;
//    }

    public Cursor getData4(String tentaikhoan){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery( "select DISTINCT  * from dsyeuthich WHERE tentaikhoan = '"+tentaikhoan+"'" , null );

        return res;
    }

    public Cursor getTruyen4(String tieude)
    {
        SQLiteDatabase db= this.getReadableDatabase();

        Cursor res =  db.rawQuery( "select * from truyen WHERE tieude = '"+tieude+"' " , null );
        return res;
    }


    //Lấy tất cả truyện
    public Cursor getData25(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_TRUYEN +" ORDER BY idtruyen DESC",null);
        return res;
    }

    //Lấy tất cả truyện
    public Cursor getData2(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_TRUYEN +" ORDER BY soluongyt DESC",null);
        return res;
    }



    //Lấy tất cả đánh giá
    public Cursor getData3(String tentr){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM danhgia where tieude='"+tentr+"'  ORDER BY iddanhgia DESC",null);
        return res;
    }

    public void DeleteYT(String tieude,String tentaikhoan){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM dsyeuthich WHERE tieude='"+tieude+"' AND tentaikhoan='"+tentaikhoan+"'");
        Log.e("xóa Yêu thích: ","Đã xóa danh sách yêu thích");
        db.close();

        String y="(soluongyt - 1)";
        SQLiteDatabase db2 = this.getWritableDatabase();
        db2.execSQL("UPDATE truyen set  soluongyt="+y+ " WHERE tieude='"+tieude+"'");
        db2.close();

    }

    public void Deletetk(String tentaikhoan){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM taikhoan WHERE tentaikhoan='"+tentaikhoan+"'");
        db.close();

        SQLiteDatabase db1 = this.getReadableDatabase();
        Cursor res1 = db1.rawQuery("SELECT * FROM "+TABLE_DS_YEU_THICH +" where tentaikhoan = '"+tentaikhoan+"'",null);
        while (res1.moveToNext())
        {
            String tieude1=res1.getString(1);
            String y="(soluongyt - 1)";
            SQLiteDatabase db3 = this.getWritableDatabase();
            db3.execSQL("UPDATE truyen set  soluongyt="+y+ " WHERE tieude='"+tieude1+"'");
            db3.close();
        }

        SQLiteDatabase db2=this.getWritableDatabase();
        db2.execSQL("DELETE FROM dsyeuthich WHERE tentaikhoan='"+tentaikhoan+"'");
        db2.close();


    }

    //Xóa truyện với id = i
    public void Delete(String tieude){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM truyen WHERE tieude='"+tieude+"'");
        db.close();

        SQLiteDatabase db2=this.getWritableDatabase();
        db2.execSQL("DELETE FROM dsyeuthich WHERE tieude='"+tieude+"'");
        db2.close();


    }
    public void updatetruyen(String tieude,String noidung,String anh,int soluongyt)
    {
//        SQLiteDatabase db= this.getWritableDatabase();
//        db.execSQL("UPDATE truyen set   noidung='"+noidung+"' , anh =' "+anh+ "' WHERE tieude='"+tieude+"'");
//        db.close();
        SQLiteDatabase db1 = this.getReadableDatabase();
        db1.execSQL("DELETE FROM truyen WHERE tieude='"+tieude+"'");
        db1.close();


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN_TRUYEN,tieude);
        values.put(NOI_DUNG,noidung);
        values.put(IMAGE,anh);
        values.put(SL_YEUTHICH,soluongyt);
        db.insert(TABLE_TRUYEN,null,values);
        db.close();
        Log.e("Add Truyện : ","Thành công");

    }

    public void updatetaikhoan(String pass,String mail,String tentaikhoan){
        SQLiteDatabase db =this.getWritableDatabase();
        db.execSQL("UPDATE taikhoan set  matkhau='"+pass+"' , email='"+mail+"' WHERE tentaikhoan='"+tentaikhoan+"'");
        db.close();

    }
    public Cursor getpass(String tentaikhoan)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_TAIKHOAN +" WHERE tentaikhoan= '"+tentaikhoan+"'",null);
        return res;
    }

}
