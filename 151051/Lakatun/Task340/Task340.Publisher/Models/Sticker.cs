using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Task340.Publisher.Models;

[Table("tbl_Tag")]
public class Tag : BaseModel
{
    [Required]
    [MinLength(2)]
    [MaxLength(32)]
    [Column(TypeName = "text")]
    public string Name { get; set; }

    public virtual ICollection<News> News { get; set; } = [];
}