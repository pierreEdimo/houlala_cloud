using Microsoft.AspNetCore.Mvc;
using AutoMapper;
using houlala_tv.Dto;
using Microsoft.EntityFrameworkCore;
using houlala_tv.Models;

namespace houlala_tv.Controllers
{
    [ApiController]
    [Produces("application/json")]
    [Route("api/[controller]")]
    public class TopicsController : ControllerBase
    {

        private readonly DatabaseContext _context;
        private readonly IMapper _mapper;

        public TopicsController(
            DatabaseContext context,
            IMapper mapper
        )
        {
            _context = context;
            _mapper = mapper;
        }

        [HttpGet]
        public async Task<ActionResult<List<TopicDto>>> GetAllTopics()
        {
            var queryable = _context!.topics!.AsQueryable();

            var topics = await queryable.ToListAsync();

            return _mapper.Map<List<TopicDto>>(topics);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<TopicDto>> GetTopic(int Id)
        {
            var topic = await _context!.topics!.FirstOrDefaultAsync(x => x.Id == Id);

            if (topic == null) return NotFound();

            return _mapper.Map<TopicDto>(topic);
        }

        [HttpPost]
        public async Task<ActionResult> AddTopic([FromBody] CreateTopicDto newTopic)
        {
            var topic = _mapper.Map<Topic>(newTopic);

            _context.Add(topic);

            await _context.SaveChangesAsync();

            var topicDto = _mapper.Map<TopicDto>(topic);

            return CreatedAtAction("GetTopic", new { Id = topicDto.Id }, topicDto);
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> editTopic(int Id, [FromBody] CreateTopicDto newTopic)
        {
            var topicDB = await _context.topics!.FirstOrDefaultAsync(x => x.Id == Id);

            if (topicDB == null) return NotFound();

            topicDB = _mapper.Map(newTopic, topicDB);

            await _context.SaveChangesAsync();

            return NoContent();
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> deleteTopic(int Id)
        {
            var topic = await _context.topics!.FirstOrDefaultAsync(x => x.Id == Id);

            if (topic == null) return NotFound();

            _context.topics!.Remove(topic);

            await _context.SaveChangesAsync();

            return NoContent();
        }

    }
}