using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Task330.Publisher.Models;

[Table("tbl_Sticker")]
public class Sticker : BaseModel
{
    [Required]
    [MinLength(2)]
    [MaxLength(32)]
    [Column(TypeName = "text")]
    public string Name { get; set; }

    public virtual ICollection<News> News { get; set; } = [];
}